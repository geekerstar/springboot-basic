package com.geekerstar.basic.aqs;

import org.junit.Test;

import java.util.Vector;

/**
 * @author geekerstar
 * @date 2022/4/5 20:15
 */
public class JoinTest {

    /**
     * https://mp.weixin.qq.com/s?__biz=MzI5MzYzMDAwNw==&mid=2247486022&idx=2&sn=994d22a73d043512646af9cc92a32730&chksm=ec6e731edb19fa082e641ec731256f52473a515d6191f1399a95445b20b8c81af3b2be68176e&mpshare=1&scene=1&srcid=#rd
     */
    @Test
    public void test1() throws InterruptedException {
        Vector<Thread> vector = new Vector<>();
        for (int i = 0; i < 5; i++) {
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
                }

            });
            vector.add(childThread);
            childThread.start();
        }
        for (Thread thread : vector) {
            thread.join();
        }
        System.out.println("主线程被执行");
    }
}
