package com.geekerstar.basic.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author geekerstar
 * @date 2022/6/21 15:45
 */
public class InterruptTest {

    private static boolean isStop = false;
    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
//        interrupt();
//        atomicBoolean();
        volatileTest();
    }

    private static void volatileTest() {
        new Thread(() -> {
            while (true) {
                if(isStop) {
                    System.out.println(Thread.currentThread().getName()+"\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello volatile");
            }
        },"t1").start();

        //暂停毫秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> isStop = true,"t2").start();
    }

    private static void atomicBoolean() {
        new Thread(() -> {
            while (true) {
                if(atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName()+"\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello atomicBoolean");
            }
        },"t1").start();

        //暂停毫秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> atomicBoolean.set(true),"t2").start();
    }

    private static void interrupt() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()+"\t isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello interrupt api");
            }
        }, "t1");
        t1.start();

        System.out.println("-----t1的默认中断标志位："+t1.isInterrupted());

        //暂停毫秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t2向t1发出协商，将t1的中断标志位设为true希望t1停下来
        new Thread(t1::interrupt,"t2").start();
        //t1.interrupt();
    }
}
