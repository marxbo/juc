package com.concurrent.chapter01;

import java.util.concurrent.*;

/**
 * Callable + Future + ExecutorService 创建带返回值的线程
 *
 * Callable接口与Runnable接口的区别？
 *      (1)是否有返回值
 *      (2)是否抛异常
 *      (3)实现方法不同：call()和run()
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 0:05
 */
public class CallableDemo implements Callable<String> {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new CallableDemo());
        try {
            // 若线程未处理完成，则一直阻塞get()方法
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        return "Callable + Future 创建线程";
    }
}
