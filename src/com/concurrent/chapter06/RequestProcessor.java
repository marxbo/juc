package com.concurrent.chapter06;

/**
 * 请求处理器【责任链模式】
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 15:44
 */
public interface RequestProcessor {

    void processorRequest(Request request);

}
