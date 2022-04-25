package com.geekerstar.basic.aqs;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author geekerstar
 * @date 2022/4/5 20:18
 */
public class CyclicBarrierTest {

    /**
     * https://mp.weixin.qq.com/s?__biz=MzI5MzYzMDAwNw==&mid=2247486022&idx=2&sn=994d22a73d043512646af9cc92a32730&chksm=ec6e731edb19fa082e641ec731256f52473a515d6191f1399a95445b20b8c81af3b2be68176e&mpshare=1&scene=1&srcid=#rd
     */
    @Test
    public void test1() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < 4; i++) {
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            });
            childThread.start();
        }
        barrier.await();//阻塞当前线程直到latch中的值
        System.out.println("主线程被执行");
    }
}
