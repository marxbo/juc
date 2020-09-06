package com.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断thread.interrupt()
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/3 13:36
 */
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "InterruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.isInterrupted()); // 未中断false
        thread.interrupt();
        System.out.println(thread.isInterrupted()); // 已中断true
    }

}
