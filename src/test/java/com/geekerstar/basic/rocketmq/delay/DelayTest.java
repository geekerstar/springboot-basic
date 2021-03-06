package com.geekerstar.basic.rocketmq.delay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author geekerstar
 * @date 2022/2/22 22:27
 * <p>
 * Producer将消息发送到Broker后，Broker会首先将消息写入到CommitLog文件，然后需要将其分发到相应的ConsumeQueue。不过在分发之前，系统会先判断消息中是否带有延时等级。若没有，则直接正常分发；若有则需要经历一个复杂的过程：
 * 1、修改消息的Topic为SCHEDULE_TOPIC_XXXX
 * 2、根据延时等级，在ConsumeQueue目录中SCHEDULE_TOPIC_XXXX主题下创建出相应的queueId,目录与ConsumeQueue文件（如果没有这些目录与文件的话）。延迟等级delayLevel与queueId的对应关系为queueId = delayLevel -1。需要注意，在创建queueId目录时，并不是一次性地将所有延迟等级对应的目录全部创建完毕，而是用到哪个延迟等级创建哪个目录
 * 3、修改消息索引单元内容。索引单元中的Message Tag HashCode部分原本存放的是消息的Tag的Hash值。现修改为消息的投递时间。投递时间是指该消息被重新修改为原Topic后再次被写入到CommitLog中的时间。 投递时间 = 消息存储时间 + 延时等级时间。消息存储时间指的是消息被发送到Broker时的时间戳。
 * 4、将消息索引写入到SCHEDULE_TOPIC_XXXX主题下相应的ConsumeQueue中
 * <p>
 * Q:SCHEDULE_TOPIC_XXXX目录中各个延时等级Queue中的消息是如何排序的？
 * A:是按照消息投递时间排序的。一个Broker中同一等级的所有延时消息会被写入到ConsumeQueue目录中SCHEDULE_TOPIC_XXXX目录下相同Queue中。即一个Queue中消息投递时间的延迟等级时间是相同的。那么投递时间就取决于于消息存储时间了。即按照消息被发送到Broker的时间进行排序的。
 * <p>
 * 投递延时消息
 * Broker内部有⼀个延迟消息服务类ScheduleMessageService，其会消费SCHEDULE_TOPIC_XXXX中的消息，即按照每条消息的投递时间，将延时消息投递到⽬标Topic中。不过在投递之前会从CommitLog中将原来写入的消息再次读出，并将其原来的延时等级设置为0，即原消息变为了一条不延迟的普通消息。然后再次将消息投递到目标Topic中。
 * ScheduleMessageService在Broker启动时，会创建并启动一个定时器Timer，用于执行相应的定时任务。系统会根据延时等级的个数，定义相应数量的TimerTask，每个TimerTask负责一个延迟等级消息的消费与投递。每个TimerTask都会检测相应Queue队列的第一条消息是否到期。若第一条消息未到期，则后面的所有消息更不会到期（消息是按照投递时间排序的）；若第一条消息到期了，则将该消息投递到目标Topic，即消费该消息。
 * <p>
 * 将消息重新写入CommitLog
 * 延迟消息服务类ScheduleMessageService将延迟消息再次发送给了CommitLog，并再次形成新的消息索引条目，分发到相应Queue。
 * 这其实就是一次普通消息发送。只不过这次的消息Producer是延迟消息服务类ScheduleMessageService。
 */
public class DelayTest {

    private static final String mqUrl = "139.155.88.184:9876";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("geek-delay-consumer");
        consumer.setNamesrvAddr(mqUrl);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("geek-delay-topic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : messageExtList) {
                    // 输出消息被消费的时间
                    System.out.print("消费者接收消息时间" + new SimpleDateFormat("mm:ss").format(new Date()) + "\n" + messageExt + "\n" + new String(messageExt.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }

    @Test
    public void delayProducer() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-delay-delayProducer");
        producer.setNamesrvAddr(mqUrl);
        producer.start();
        for (int i = 0; i < 1; i++) {
            String msg = "延迟消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            Message message = new Message("geek-delay-topic", "delayProducer", body);
            // 指定消息延迟等级为3级，即延迟10s
            message.setDelayTimeLevel(3);
            SendResult sendResult = producer.send(message);
            // 输出消息被发送的时间
            System.out.print("生产者发送消息时间：" + new SimpleDateFormat("mm:ss").format(new Date()) + "\n" + sendResult);
        }
        producer.shutdown();
    }


}
