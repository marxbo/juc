package com.concurrent.chapter06;

/**
 * 请求【责任链模式】
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/2 15:43
 */
public class Request {

    private String name;

    public Request() {}

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
