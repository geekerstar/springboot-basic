package com.geekerstar.basic.java8.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2022/3/8 19:41
 */
public class CompletableFutureTest {

    /**
     * runAsync() 异步无参返回
     *
     * @throws Exception
     */
    @Test
    public void asyncThread() throws Exception {
        CompletableFuture async1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                System.out.println("none return Async");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 调用get()将等待异步逻辑处理完成
        async1.get();
    }

    /**
     * supplyAsync() 异步有参返回
     *
     * @throws Exception
     */
    @Test
    public void asyncThread2() throws Exception {
        CompletableFuture<String> async2 = CompletableFuture.supplyAsync(() -> "hello");
        String result = async2.get();
        // String result2 = async2.get(5L, TimeUnit.SECONDS);
        System.out.println(result);
    }

    /**
     * allOf() 多个异步处理(针对有参返回)
     *
     * @throws Exception
     */
    @Test
    public void asyncThread3() throws Exception {
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "youth");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture all = CompletableFuture.allOf(a, b, c);
        all.get();

        String result = Stream.of(a, b, c)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    /**
     * anyOf() 多个异步随机处理(针对有参返回)
     *
     * @throws Exception
     */
    @Test
    public void asyncThread4() throws Exception {
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20);
                return "hello";
            } catch (Exception e) {
                e.printStackTrace();
                return "none~";
            }
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "youth");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture<Object> any = CompletableFuture.anyOf(a, b, c);
        String result = (String) any.get();

        System.out.println(result);
    }
}
