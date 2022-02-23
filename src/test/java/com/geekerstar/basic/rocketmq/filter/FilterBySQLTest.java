package com.geekerstar.basic.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

/**
 * @author geekerstar
 * @date 2022/2/23 21:57
 *
 * 对于指定Topic消息的过滤有两种过滤方式：Tag过滤与SQL过滤。
 *
 * 1、Tag过滤
 * 通过consumer的subscribe()方法指定要订阅消息的Tag。如果订阅多个Tag的消息，Tag间使用或运算 符(双竖线||)连接。
 *
 * 2、SQL过滤
 * SQL过滤是一种通过特定表达式对事先埋入到消息中的用户属性进行筛选过滤的方式。通过SQL过滤， 可以实现对消息的复杂过滤。不过，只有使用PUSH模式的消费者才能使用SQL过滤。
 *
 * SQL过滤表达式中支持多种常量类型与运算符。
 * 支持的常量类型：
 * - 数值：比如：123，3.1415
 * - 字符：必须用单引号包裹起来，比如：'abc'
 * - 布尔：TRUE 或 FALSE
 * - NULL：特殊的常量，表示空
 *
 * 支持的运算符有：
 * - 数值比较：>，>=，<，<=，BETWEEN，=
 * - 字符比较：=，<>，IN
 * - 逻辑运算 ：AND，OR，NOT
 * - NULL判断：IS NULL 或者 IS NOT NULL
 *
 * 默认情况下Broker没有开启消息的SQL过滤功能，需要在Broker加载的配置文件中添加如下属性，以开 启该功能：
 * enablePropertyFilter = true
 *
 * 在启动Broker时需要指定这个修改过的配置文件。例如对于单机Broker的启动，其修改的配置文件是 conf/broker.conf，启动时使用如下命令：
 * sh bin/mqbroker -n localhost:9876 -c conf/broker.conf &
 */
public class FilterBySQLTest {

    private static final String mqUrl = "139.155.88.184:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pg");
        consumer.setNamesrvAddr(mqUrl);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 要从TopicE的消息中过滤出age在[0, 6]间的消息
        consumer.subscribe("TopicE", MessageSelector.bySql("age between 0 and 6"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt me:msgs){
                    System.out.println(me);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }

    @Test
    public void filterBySQLProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr(mqUrl);
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                byte[] body = ("Hi," + i).getBytes();
                Message msg = new Message("TopicE", "myTag", body);
                // 事先埋入用户属性age
                msg.putUserProperty("age", i + "");
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
