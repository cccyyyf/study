package com.cyfhandsome.up.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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
        if (++threadCount <= coreSize) {
            new Worker(command).start();
        }else {
            try {
                workQueue.put(command);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * 运行线程
     */
    private class Worker extends Thread {
        private Runnable firstTask;

        public Worker(Runnable runnable) {
            super(String.format("Worker-%d", COUNTER.getAndIncrement()));
            this.firstTask = runnable;
        }

        @Override
        public void run() {
            Runnable task = this.firstTask;
            while (null != task || null != (task = getTask())) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
        }


    }

    /**
     * 获取消息队列中的线程
     *
     * @return 线程
     */
    public Runnable getTask() {
        try {
            return workQueue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CoreThreadPool coreThreadPool = new CoreThreadPool(5);
        IntStream.range(0,10).forEach(i -> coreThreadPool.execute(() ->
                System.out.printf("Thread:%s,value:%d%n"
                        , Thread.currentThread().getName(), i)));
        Thread.sleep(Integer.MAX_VALUE);
    }

}
