package com.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * 在.class文件位置使用JDK内置工具【jps, jstak [PID]】命令可以跟踪线程状态
 * 线程6个状态：
 * (1)NEW：调用thread.start()方法
 * (2)RUNNABLE：包含2个子状态【就绪(READY)、运行(RUNNING)】
 *      READY =系统调度=> RUNNING
 *      RUNNING =yield()=> READY
 * (3)BLOCKED：同步锁被占用，等待释放
 * (4)WAITING：Object.wait()/notify()、Object.join()、LockSupport.park()/unpark()
 * (5)TIMED_WAITING：Thread.sleep()、Object.wait()/notify()、Object.join()、LockSupport.park()/unpark()
 * (6)TERMINATED：run()方法执行结束
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 19:34
 */
public class ThreadStatusDemo {

    public static void main(String[] args) {
        // 线程状态：TIMED_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "time_waiting").start();

        // 线程状态：WAITING
        new Thread(() -> {
            synchronized (ThreadStatusDemo.class) {
                while (true) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waiting").start();

        // 线程状态：TIMED_WAITING
        new Thread(new BlockedDemo(), "BlockedDemo-0").start();
        // 线程状态：BLOCKED
        new Thread(new BlockedDemo(), "BlockedDemo-1").start();
    }

    static class BlockedDemo extends Thread {

        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
