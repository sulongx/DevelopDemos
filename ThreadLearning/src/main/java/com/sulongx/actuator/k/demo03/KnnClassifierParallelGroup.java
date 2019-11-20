package com.sulongx.actuator.k.demo03;

import com.sulongx.actuator.k.common.Distance;
import com.sulongx.actuator.k.common.Sample;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Sulongx
 */
public class KnnClassifierParallelGroup {
    private final List<? extends Sample> dataSet;
    private final int k;
    private final int threadNumbs;
    private final ThreadPoolExecutor executor;
    private boolean parallelSort;

    public KnnClassifierParallelGroup(List<? extends Sample> dataSet, int k, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.parallelSort = parallelSort;
        this.threadNumbs = k * (Runtime.getRuntime().availableProcessors());
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadNumbs);
    }

    public String classify(Sample example) throws InterruptedException {
        Distance[] distances = new Distance[dataSet.size()];
        CountDownLatch endController = new CountDownLatch(dataSet.size());
        int length = dataSet.size() / threadNumbs;
        int startIndex = 0,endIndex = length;
        for(int i = 0;i < threadNumbs;i ++){
            GroupDistanceTask task = new GroupDistanceTask(distances, startIndex, endIndex,example, dataSet, endController);
            startIndex = endIndex;
            if(i < threadNumbs - 2){
                endIndex += length;
            }else {
                endIndex = dataSet.size();
            }
            executor.execute(task);
        }
        endController.await();

        Map<String,Integer> results = new HashMap<>();
        for(int i = 0;i < k;i ++){
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag,1,(a,b) -> a + b);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
