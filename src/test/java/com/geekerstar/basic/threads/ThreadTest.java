package com.geekerstar.basic.threads;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2022/4/25 21:13
 */
public class ThreadTest {

    public static void main(String[] args) {
        // 创建任务队列，共 10 个线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        // 执行任务: 1秒 后开始执行，每 30秒 执行一次
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("执行任务：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }, 10, 30, TimeUnit.SECONDS);
    }
}
