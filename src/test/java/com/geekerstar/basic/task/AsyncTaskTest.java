package com.geekerstar.basic.task;

import com.geekerstar.basic.BasicApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author geekerstar
 * @date 2021/8/18 17:05
 * @description
 */
@Slf4j
public class AsyncTaskTest extends BasicApplicationTests {
    @Autowired
    private AsyncTask task;
    @Autowired
    private AsyncTask2 asyncTask2;

    /**
     * 测试异步任务
     */
    @Test
    public void asyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<Boolean> asyncTask1 = task.asyncTask1();
        Future<Boolean> asyncTask2 = task.asyncTask2();
        Future<Boolean> asyncTask3 = task.asyncTask3();

        // 调用 get() 阻塞主线程
        asyncTask1.get();
        asyncTask2.get();
        asyncTask3.get();
        long end = System.currentTimeMillis();

        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }

    /**
     * 测试同步任务
     */
    @Test
    public void taskTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }


    @Test
    public void test() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture<String> task1 = asyncTask2.doTaskOne();
        CompletableFuture<String> task2 = asyncTask2.doTaskTwo();
        CompletableFuture<String> task3 = asyncTask2.doTaskThree();

        CompletableFuture.allOf(task1, task2, task3).join();

        log.info("任务全部完成，总耗时：" + stopWatch.getTime() + "毫秒");
    }
}
