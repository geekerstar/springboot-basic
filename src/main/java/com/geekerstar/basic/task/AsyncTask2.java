package com.geekerstar.basic.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author geekerstar
 * @date 2021/10/3 22:51
 * @description
 */
@Slf4j
@Component
public class AsyncTask2 {

    public static Random random = new Random();

    @Async
    public CompletableFuture<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        log.info("完成任务一，耗时：" + stopWatch.getTime() + "毫秒");
        return CompletableFuture.completedFuture("任务一完成");
    }

    @Async
    public CompletableFuture<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        log.info("完成任务二，耗时：" + stopWatch.getTime() + "毫秒");
        return CompletableFuture.completedFuture("任务二完成");
    }

    @Async
    public CompletableFuture<String> doTaskThree() throws Exception {
        log.info("开始做任务三");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        log.info("完成任务三，耗时：" + stopWatch.getTime() + "毫秒");
        return CompletableFuture.completedFuture("任务三完成");
    }

}
