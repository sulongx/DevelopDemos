package com.sulongx.jvm.gc;

/**
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2021/11/3 10:36
 **/
public class ModelObjectAllocation implements Runnable{
    private final int[][] allocated;

    private final int lifeTime;


    public ModelObjectAllocation(final int x, final int y, final int liveFor) {
        this.allocated = new int[x][y];
        this.lifeTime = liveFor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(lifeTime);
            System.out.println(System.currentTimeMillis() + ": " + allocated.length);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
