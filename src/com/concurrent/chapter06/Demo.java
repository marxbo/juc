package com.concurrent.chapter06;

/**
 * 测试
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 16:08
 */
public class Demo {

    PrintProcessor printProcessor;

    public Demo() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    public static void main(String[] args) {
        Request request = new Request("marxbo");
        new Demo().doTest(request);
    }

    private void doTest(Request request) {
        printProcessor.processorRequest(request);
    }


}
