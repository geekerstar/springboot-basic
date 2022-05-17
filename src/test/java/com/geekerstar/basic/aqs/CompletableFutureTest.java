package com.geekerstar.basic.aqs;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author geekerstar
 * @date 2022/5/17 16:40
 */
public class CompletableFutureTest {

    @Test
    public void test1() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "step1 result";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            return "step2 result";
        });
        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + " , " + result2);
            System.out.println("执行step 3");
            return "step3 result";
        }).thenAccept(result3 -> System.out.println(result3));
    }

    @Test
    public void test2() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        }, executor);
        //2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
        //3、先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("success");
    }

    @Test
    public void test3() {
        ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            //业务操作
            return "";
        }, threadPool1);
        //此时，如果future1中的业务操作已经执行完毕并返回，则该thenApply直接由当前main线程执行；否则，将会由执行以上业务操作的threadPool1中的线程执行。
        future1.thenApply(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });
        //使用ForkJoinPool中的共用线程池CommonPool
        future1.thenApplyAsync(value -> {
            //do something
            return value + "1";
        });
        //使用指定线程池
        future1.thenApplyAsync(value -> {
            //do something
            return value + "1";
        }, threadPool1);

    }
}
