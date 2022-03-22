package com.cyfhandsome.up.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cyf
 * @date 2022/3/17 21:43
 */
public class CoreThreadPool implements Executor {

    /**
     * 工作队列,当核心线程满的时候,将任务放到工作队列中
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 核心线程数
     */
    private int coreSize;

    /**
     * 线程总数
     */
    private int threadCount = 0;

    /**
     * 计数器
     */
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public CoreThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.workQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void execute(Runnable command) {

    }
}
