package com.concurrent.chapter02;

/**
 * 利用volatile的可见性中断线程：
 *
 * 1、volatile如何保证可见性？
 * 答: (1)Lock前缀指令会引起处理器缓存回写到主内存。
 *     (2)这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。
 *        => 处理器使用MESI【修改、独占、共享、无效】控制协议去维护内部缓存和其他处理器缓存的一致性
 *
 * 2、注意：volatile只能修饰类变量和成员变量
 *
 * 3、volatile写和读的内存语义：
 *      (1)当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存
 *      (2)当读一个volatile变量时，JMM会把本地内存置为无效。线程会从主内存中读取共享变量
 *
 * 4、volatile变量特性：
 *      (1)可见性：对一个volatile变量的读，总是能看到对这个volatile变量最后的写入。
 *      (2)原子性：对任意单个volatile变量的读写具有原子性，但类似于i++这种复合操作不能保证原子性。
 *      (3)有序性：volatile修饰的变量会禁止指令重排序。
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/3 14:45
 */
public class VisibleDemo {

    /**
     * 中断标志：被volatile修饰的变量在被修改后可以立即同步到主内存，每次读之前都从主内存刷新。
     */
    private volatile static boolean isStop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("start thread");
            int i = 0;
            while (!isStop) {
                i++;
            }
            System.out.println("stop thread");
        }, "VolatileDemo");
        thread.start();
        Thread.sleep(3000);
        isStop = true;
    }

}
