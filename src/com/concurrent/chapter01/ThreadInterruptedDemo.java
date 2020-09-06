package com.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断复位的2种方法：
 *      (1)Thread.interrupted()
 *      (2)抛出中断异常
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/3 13:56
 */
public class ThreadInterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        /*

        Thread thread = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    // true
                    System.out.println("before: " + in);
                    Thread.interrupted(); // 中断复位(即清除中断标志，设置为false)
                    // false
                    System.out.println("after: " + Thread.currentThread().isInterrupted());
                    break;
                }
            }
        }, "ThreadInterruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); // 中断线程

         */

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // 抛出该异常，也会清除中断标志，设置为false)
                    e.printStackTrace();
                }
            }
        }, "ThreadInterruptedDemo2");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        // 设置中断标志为true，但sleep方法抛出中断异常，中断复位
        thread2.interrupt();
        TimeUnit.SECONDS.sleep(1);
        // false
        System.out.println(thread2.isInterrupted());

    }

}
