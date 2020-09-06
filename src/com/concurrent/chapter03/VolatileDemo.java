package com.concurrent.chapter03;

/**
 * 指令重排序：在执行程序时，为了提高性能，编译器和处理器常常会对指令做重排序。
 *
 * Java的并发采用的是【共享内存模型】：
 *      (1)线程之间的共享变量存储在【主内存中】，每个线程都有一个私有的【本地内存】，本地内存中存储了该线程以读/写【共享变量的副本】。
 *      (2)本地内存是JMM的一个抽象概念，并不真实存在。
 *
 * 线程之间的通信机制有两种：【共享内存】和【消息传递】。
 *
 * JMM(Java内存模型)通过禁止特定类型的【编译器重排序】和【处理器重排序】，保证【内存可见性】。
 *      (1)对于编译器，JMM的编译器重排序规则会禁止特定类型的编译器重排序(不是所有的编译器重排序都要禁止)。
 *      (2)对于处理器重排序，JMM的处理器重排序规则会要求Java编译器在生成指令序列时，插入特定类型的内存屏障(Memory Barriers，Intel称之为Memory Fence)指令，
 *         通过【内存屏障指令】来禁止特定类型的处理器重排序。
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/4 10:50
 */
public class VolatileDemo {

    /**
     * as-if-serial语义：不管怎么重排序，(单线程)程序的执行结果不能被改变。
     *      为了遵守as-if-serial语义，编译器和处理器不会对存在【数据依赖关系】的操作做重排序，因为这种重排序会改变执行结果。
     *      但是，如果操作之间不存在数据依赖关系，这些操作就可能被编译器和处理器重排序。
     */

    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread t2 = new Thread(() -> {
            b = 1;
            y = a;
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        /**
         * 会出现4种结果：
         * (1)x=1, y=0
         * (2)x=0, y=1
         * (3)x=1, y=1
         * (4)x=0, y=0【几乎不会出现，但是允许出现】
         */
        System.out.println("x=" + x + ", y=" + y);
    }

}
