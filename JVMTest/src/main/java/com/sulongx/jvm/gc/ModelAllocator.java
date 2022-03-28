package com.sulongx.jvm.gc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2021/11/2 10:54
 **/
public class ModelAllocator implements Runnable{

    private volatile boolean shutdown = false;

    private double chanceOfLongLived = 0.02d;

    private int multiplierForLongLived = 20;

    private int x = 1024;

    private int y = 1024;

    private int mbPerSec = 50;

    private int shortLivedMs = 100;

    private int nThreads = 8;

    private Executor executor = Executors.newFixedThreadPool(nThreads);


    @Override
    public void run() {
        final int mainSleep = (int) (1000.0 / mbPerSec);

        while (!shutdown){
            for (int i = 0; i < mbPerSec; i++) {
                ModelObjectAllocation to = new ModelObjectAllocation(x, y, lifeTime());
                executor.execute(to);
                try {
                    Thread.sleep(mainSleep);
                } catch (InterruptedException e) {
                    shutdown = true;
                }
            }
        }
    }


    /**
     * 用来模拟`弱分代假说`的简单函数
     *
     * @return 返回对象的预期寿命
     */
    public int lifeTime(){
        if(Math.random() < chanceOfLongLived){
            return multiplierForLongLived * shortLivedMs;
        }
        return shortLivedMs;
    }
}
