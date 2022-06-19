package com.geekerstar.basic.threads.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2022/6/12 08:58
 *
 * https://mp.weixin.qq.com/s/dzMVt-vqkWLLe-AZnn2Bug
 */
public class TransmittableThreadLocalTest {

    @Test
    public void test() throws Exception {
        //单一线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //InheritableThreadLocal存储
        InheritableThreadLocal<String> username = new InheritableThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            username.set("线程—" + i);
            Thread.sleep(3000);
            CompletableFuture.runAsync(() -> System.out.println(username.get()), executorService);
        }
    }

    @Test
    public void test2() throws Exception {
        //单一线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //需要使用TtlExecutors对线程池包装一下
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        //TransmittableThreadLocal创建
        TransmittableThreadLocal<String> username = new TransmittableThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            username.set("线程—" + i);
            Thread.sleep(3000);
            CompletableFuture.runAsync(() -> System.out.println(username.get()), executorService);
        }
    }
}
