package com.geekerstar.basic.rocketmq.retry;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2022/2/23 22:18
 */
public class RetryTest {

    private static final String mqUrl = "139.155.88.184:9876";

    public static void main(String[] args) throws MQClientException {
        // 定义一个pull消费者
        // DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("cg");
        // 定义一个push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("geek-retry-consumer");
        // 指定nameServer
        consumer.setNamesrvAddr(mqUrl);
        // 指定从第一条消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 指定消费topic与tag
        consumer.subscribe("geek-retry-topic", "*");
        // 指定采用“广播模式”进行消费，默认为“集群模式”
        // consumer.setMessageModel(MessageModel.BROADCASTING);
        // 顺序消息消费失败的消费重试时间间隔，默认为1000毫秒，其取值范围为[10, 30000]毫秒
        consumer.setSuspendCurrentQueueTimeMillis(100);
        // 修改消费重试次数
        consumer.setMaxReconsumeTimes(20);
        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 一旦broker中有了其订阅的消息就会触发该方法的执行，
            // 其返回值为当前consumer消费的状态
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
                // 逐条消费消息
                for (MessageExt messageExt : messageExtList) {
                    System.out.println(messageExt + "\n" + new String(messageExt.getBody()));
                }
                // 返回消费状态：消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 开启消费者消费
        consumer.start();
        System.out.println("Consumer Started");
    }

    @Test
    public void retrySyncProducer() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建一个producer，参数为Producer Group名称
        DefaultMQProducer producer = new DefaultMQProducer("geek-retry-retrySyncProducer");
        // 指定nameServer地址
        producer.setNamesrvAddr(mqUrl);
        // 设置同步发送失败时重试发送的次数，默认为2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);
        // 开启生产者
        producer.start();
        // 生产并发送10条消息
        for (int i = 0; i < 10; i++) {
            String msg = "重试同步消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            Message message = new Message("geek-retry-topic", "retrySyncProducer", body);
            // 为消息指定key
            message.setKeys("key-" + i);
            // 同步发送消息
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        // 关闭producer
        producer.shutdown();
    }

    @Test
    public void retryAsyncProducer() throws InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-retry-retryAsyncProducer");
        producer.setNamesrvAddr(mqUrl);
        // 指定异步发送失败后不进行重试发送
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 指定新创建的Topic的Queue数量为2，默认为4
        producer.setDefaultTopicQueueNums(2);
        producer.start();
        for (int i = 0; i < 10; i++) {
            String msg = "重试异步消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            try {
                Message message = new Message("geek-retry-topic", "retryAsyncProducer", body);
                // 异步发送。指定回调
                producer.send(message, new SendCallback() {
                    // 当producer接收到MQ发送来的ACK后就会触发该回调方法的执行
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // end-for

        // sleep一会儿
        // 由于采用的是异步发送，所以若这里不sleep，
        // 则消息还未发送就会将producer给关闭，报错
        TimeUnit.SECONDS.sleep(3);
        producer.shutdown();
    }
}
