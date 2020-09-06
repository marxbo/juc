package com.concurrent.chapter06;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 打印处理线程【责任链模式】
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 15:46
 */
public class PrintProcessor extends Thread implements RequestProcessor{

    /**
     * 请求队列
     */
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    /**
     * 下一个处理线程
     */
    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                System.out.println("打印请求：" + request);
                nextProcessor.processorRequest(request);
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
