package com.geekerstar.basic.rocketmq.filter;

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

import java.util.List;

/**
 * @author geekerstar
 * @date 2022/2/23 21:59
 */
public class FilterByTagTest {

    private static final String mqUrl = "139.155.88.184:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("geek-filter-consumer");
        consumer.setNamesrvAddr(mqUrl);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 仅订阅Tag为myTagA与myTagB的消息，不包含myTagC
        consumer.subscribe("geek-filter-topic", "tagA || tagB");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : messageExtList) {
                    System.out.println(messageExt);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }


    @Test
    public void FilterByTagProducer() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-filter-filterByTagProducer");
        producer.setNamesrvAddr(mqUrl);
        producer.start();

        // 发送的消息均包含Tag，为以下三种Tag之一
        String[] tags = {"tagA", "tagB", "tagC"};
        for (int i = 0; i < 10; i++) {
            String msg = "TAG过滤消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            String tag = tags[i % tags.length];
            Message message = new Message("geek-filter-topic", tag, body);
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
