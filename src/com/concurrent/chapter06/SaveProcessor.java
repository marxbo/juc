package com.concurrent.chapter06;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 保存处理线程【责任链模式】
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 15:46
 */
public class SaveProcessor extends Thread implements RequestProcessor{

    /**
     * 请求队列
     */
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                System.out.println("保存请求：" + request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processorRequest(Request request) {
        linkedBlockingQueue.add(request);
    }
}
