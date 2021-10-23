package com.geekerstar.basic.apm;

/**
 * @author geekerstar
 * @date 2021/10/23 09:49
 * <p>
 * 使用ThreadLocal传递上下文信息
 */
public class MetricHolder {
    public static ThreadLocal<String> metric = new ThreadLocal<>();
}
