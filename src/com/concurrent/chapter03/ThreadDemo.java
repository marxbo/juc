package com.concurrent.chapter03;

/**
 * volatile关键字和lock前缀指令
 *
 * 1、下载hsdis-amd64.dll，放在$JAVA_HOME/jre/bin/server
 * 2、Edit Configurations...
 *      (1)指定VM options运行参数：打印指定方法的汇编指令
 *      -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*ThreadDemo.getInstance
 *      (2)指定jre路径：$JAVA_HOME/jre/
 *
 * @author marxbo
 * @version 1.0
 * @date 2020/2/4 9:06
 */
public class ThreadDemo {

    /**
     * 加/不加volatile关键字打印出来的汇编指令区别：有/无【lock前缀指令】
     *
     * lock前缀指令的作用？
     * 答: (1)Lock前缀指令会引起处理器缓存回写到主内存。
     *     (2)这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。
     *        => 处理器使用MESI【修改、独占、共享、无效】控制协议去维护内部缓存和其他处理器缓存的一致性
     */
    private static volatile ThreadDemo instance = null;

    public static ThreadDemo getInstance() {
        if (instance == null) {
            instance = new ThreadDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadDemo.getInstance();
    }

}
