package com.geekerstar.basic.rocketmq.general;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2022/2/22 20:45
 * <p>
 * 消息发送的状态
 * SEND_OK,发送成功
 * FLUSH_DISK_TIMEOUT,刷盘超时。当Broker设置的刷盘策略为同步刷盘时才可能出现这种异常状态。异步刷盘不会出现
 * FLUSH_SLAVE_TIMEOUT,Slave同步超时。当Broker集群设置的Master-Slave的复制方式为同步复制时才可能出现这种异常状态。异步复制不会出现
 * SLAVE_NOT_AVAILABLE,没有可用的Slave。当Broker集群设置为Master-Slave的 复制方式为同步复制时才可能出现这种异常状态。异步复制不会出现
 */
public class GeneralTest {

    private static final String mqUrl = "139.155.88.184:9876";

    /**
     * 消费者
     *
     * @throws MQClientException
     */
    public static void main(String[] args) throws MQClientException {
        // 定义一个pull消费者
        // DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("cg");
        // 定义一个push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("geek-general-consumer");
        // 指定nameServer
        consumer.setNamesrvAddr(mqUrl);
        // 指定从第一条消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 指定消费topic与tag
        consumer.subscribe("geek-general-topic", "*");
        // 指定采用“广播模式”进行消费，默认为“集群模式”
        // consumer.setMessageModel(MessageModel.BROADCASTING);

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            // 一旦broker中有了其订阅的消息就会触发该方法的执行，
            // 其返回值为当前consumer消费的状态
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext context) {
                // 逐条消费消息
                for (MessageExt messageExt : msgList) {
                    System.out.println(messageExt + "\n" + new String(messageExt.getBody()) + "\n");
                }
                // 返回消费状态：消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 开启消费者消费
        consumer.start();
        System.out.println("geek-general-consumer Started");
    }


    /**
     * 同步消息发送生产者
     *
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQClientException
     */
    @Test
    public void syncProducer() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        // 创建一个producer，参数为Producer Group名称
        DefaultMQProducer producer = new DefaultMQProducer("geek-general-syncProducer");
        // 指定nameServer地址
        producer.setNamesrvAddr(mqUrl);
        // 设置当发送失败时重试发送的次数，默认为2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);
        // 开启生产者
        producer.start();

        // 生产并发送100条消息
        for (int i = 0; i < 10; i++) {
            String msg = "同步消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            Message message = new Message("geek-general-topic", "syncProducer", body);
            // 为消息指定key
            message.setKeys("key-" + i);
            // 同步发送消息
            // SEND_OK：消息发送成功。需要注意的是，消息发送到 broker 后，还有两个操作：消息刷盘和消息同步到 slave 节点，默认这两个操作都是异步的，只有把这两个操作都改为同步，SEND_OK 这个状态才能真正表示发送成功。
            //FLUSH_DISK_TIMEOUT：消息发送成功但是消息刷盘超时。
            //FLUSH_SLAVE_TIMEOUT：消息发送成功但是消息同步到 slave 节点时超时。
            //SLAVE_NOT_AVAILABLE：消息发送成功但是 broker 的 slave 节点不可用。
            SendResult sendResult = producer.send(message);
            System.out.println("sendResult：" + sendResult + ",msg：" + msg);
        }
        // 关闭producer
        producer.shutdown();
    }

    /**
     * 异步发送生产者
     * <p>
     * 异步刷盘：默认。消息写入 CommitLog 时，并不会直接写入磁盘，而是先写入 PageCache 缓存后返回成功，然后用后台线程异步把消息刷入磁盘。异步刷盘提高了消息吞吐量，但是可能会有消息丢失的情况，比如断点导致机器停机，PageCache 中没来得及刷盘的消息就会丢失。
     * 同步刷盘：消息写入内存后，立刻请求刷盘线程进行刷盘，如果消息未在约定的时间内(默认 5 s)刷盘成功，就返回 FLUSH_DISK_TIMEOUT，Producer 收到这个响应后，可以进行重试。同步刷盘策略保证了消息的可靠性，同时降低了吞吐量，增加了延迟。要开启同步刷盘，需要增加下面配置：flushDiskType=SYNC_FLUSH
     *
     * @throws MQClientException
     */
    @Test
    public void asyncProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-general-asyncProducer");
        producer.setNamesrvAddr(mqUrl);
        // 指定异步发送失败后不进行重试发送
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 指定新创建的Topic的Queue数量为2，默认为4
        producer.setDefaultTopicQueueNums(2);

        producer.start();

        for (int i = 0; i < 10; i++) {
            String msg = "异步消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            try {
                Message message = new Message("geek-general-topic", "asyncProducer", body);
                // 异步发送。指定回调
                producer.send(message, new SendCallback() {
                    // 当producer接收到MQ发送来的ACK后就会触发该回调方法的执行
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("异步发送消息收到回调：" + sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // sleep一会儿
        // 由于采用的是异步发送，所以若这里不sleep，
        // 则消息还未发送就会将producer给关闭，报错
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

    /**
     * 单向发送
     *
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    @Test
    public void onewayProducer() throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-general-onewayProducer");
        producer.setNamesrvAddr(mqUrl);
        producer.start();

        for (int i = 0; i < 10; i++) {
            String msg = "单向消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            Message message = new Message("geek-general-topic", "onewayProducer", body);
            // 单向发送
            producer.sendOneway(message);
        }
        producer.shutdown();
        System.out.println("producer shutdown");
    }

    /**
     * 顺序消息
     * <p>
     * 顺序消息指的是，严格按照消息的发送顺序进行消费的消息(FIFO)。
     * 默认情况下生产者会把消息以Round Robin轮询方式发送到不同的Queue分区队列；而消费消息时会从 多个Queue上拉取消息，这种情况下的发送和消费是不能保证顺序的。如果将消息仅发送到同一个Queue中，消费时也只从这个Queue上拉取消息，就严格保证了消息的顺序性。
     * 当发送和消费参与的Queue只有一个时所保证的有序是整个Topic中消息的顺序， 称为全局有序。
     * 如果有多个Queue参与，其仅可保证在该Queue分区队列上的消息顺序，则称为分区有序。
     * 一般性的选择算法是，让选择key（或其hash值）与该Topic所包含的Queue的数量取模，其结果 即为选择出的Queue的QueueId。
     *
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQClientException
     */
    @Test
    public void orderedProducer() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("geek-general-orderedProducer");
        producer.setNamesrvAddr(mqUrl);

        // 若为全局有序，则需要设置Queue数量为1
        // producer.setDefaultTopicQueueNums(1);
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 为了演示简单，使用整型数作为orderId
            Integer orderId = i;
            String msg = "顺序消息发送，生产者发送消息：" + i;
            byte[] body = msg.getBytes();
            Message message = new Message("geek-general-topic", "orderedProducer", body);
            // 将orderId作为消息key
            message.setKeys(orderId.toString());
            // send()的第三个参数值会传递给选择器的select()的第三个参数
            // 该send()为同步发送
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                // 具体的选择算法在该方法中定义
                @Override
                public MessageQueue select(List<MessageQueue> messageQueueList, Message msg, Object arg) {
                    // 以下是使用消息key作为选择的选择算法
                    String keys = msg.getKeys();
                    int id = Integer.parseInt(keys);
                    // 以下是使用arg作为选择key的选择算法
                    // Integer id = (Integer) arg;
                    int index = id % messageQueueList.size();
                    return messageQueueList.get(index);
                }
            }, orderId);
            System.out.println("顺序发送消息收到回调：" + sendResult);
        }
        producer.shutdown();
    }


}
