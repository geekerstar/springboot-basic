package com.geekerstar.basic.constant;

/**
 * @author geekerstar
 * @date 2021/8/18 21:53
 * @description
 */
public class RabbitmqConstant {
    /**
     * 直接模式1
     */
    public static final String DIRECT_MODE_QUEUE_ONE = "queue.direct.1";

    /**
     * 队列2
     */
    public static final String QUEUE_TWO = "queue.2";

    /**
     * 队列3
     */
    public static final String QUEUE_THREE = "3.queue";

    /**
     * 分列模式
     */
    public static final String FANOUT_MODE_QUEUE = "fanout.mode";

    /**
     * 主题模式
     */
    public static final String TOPIC_MODE_QUEUE = "topic.mode";

    /**
     * 路由1
     */
    public static final String TOPIC_ROUTING_KEY_ONE = "queue.#";

    /**
     * 路由2
     */
    public static final String TOPIC_ROUTING_KEY_TWO = "*.queue";

    /**
     * 路由3
     */
    public static final String TOPIC_ROUTING_KEY_THREE = "3.queue";

    /**
     * 延迟队列
     */
    public static final String DELAY_QUEUE = "delay.queue";

    /**
     * 延迟队列交换器
     */
    public static final String DELAY_MODE_QUEUE = "delay.mode";
}
