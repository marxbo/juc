package com.concurrent.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile的原子性：volatile只能保证单个读/写操作的原子性，不能保证复合操作的原子性
 *
 * (1)原子操作是什么？
 * 答：不可被中断的一个或一系列操作。
 *
 * (2)处理器如何实现原子操作？
 * 答：总线锁(LOCK#信号，阻塞其他CPU，独占共享内存)、缓存锁(缓存一致性机制会阻止同时修改有两个以上CPU缓存的内存区域数据)。
 *
 * (3)线程安全的3大问题？
 * 原子性：一个操作或者多个操作组合要么全部执行，并且执行的过程不会被任何因素打断，要么就都不执行。
 * 可见性：当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
 * 有序性：程序执行的顺序按照代码的先后顺序执行。
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/3 15:57
 */
public class AtomicDemo {

    /**
     * volatile只能保证单个读/写操作的原子性，不能保证复合操作的原子性
     */
    private volatile static int count = 0;

    /**
     * 加synchronized同步锁可以保证多线程对复合操作的原子性
     */
    public static /*synchronized*/ void inc() {
        /**
         * volatile变量的复合操作相当于多个同步的读/写方法：
         * (1) synchronized getCount
         * (2) synchronized count+1
         * (3) synchronized setCount
         *
         * 注：同步方法只能保证当前方法的原子性。
         * 由于锁不同，故不能保证复合操作的原子性
         */
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // 多个线程共享同一个类变量，不能保证操作的原子性
            threadList.add(new Thread(AtomicDemo::inc));
        }
        threadList.stream().forEach(Thread::start);
        // 线程合并。等所有线程执行完
        threadList.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /**
         * 输出结果小于等于20000
         * 加了同步锁后等于20000
         */
        System.out.println(count);
    }

}
