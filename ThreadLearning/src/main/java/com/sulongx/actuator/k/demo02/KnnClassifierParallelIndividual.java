package com.sulongx.actuator.k.demo02;

import com.sulongx.actuator.k.common.Distance;
import com.sulongx.actuator.k.common.Sample;
import com.sulongx.matrix.demo02.IndividualMultiplierTask;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Sulongx
 */
public class KnnClassifierParallelIndividual {
    private final List<? extends Sample> dataSet;
    private final Integer k;
    private final ThreadPoolExecutor executor;
    private final Integer numThreads;
    private final Boolean parallelSort;

    public KnnClassifierParallelIndividual(List<? extends Sample> dataSet, Integer k, Boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.numThreads = k * (Runtime.getRuntime().availableProcessors());
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        this.parallelSort = parallelSort;
    }


    public String classify(Sample sample) throws InterruptedException {
        Distance[] distances = new Distance[dataSet.size()];
        CountDownLatch endController = new CountDownLatch(dataSet.size());
        int index = 0;
        for(Sample localExample : dataSet){
            IndividualDistanceTask task = new IndividualDistanceTask(distances, index, localExample, sample, endController);
            executor.execute(task);
            index ++;
        }
        endController.await();
        if(parallelSort){
            Arrays.parallelSort(distances);
        }else {
            Arrays.sort(distances);
        }
        Map<String,Integer> results = new HashMap<>();
        for(int i = 0;i < k;i ++){
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag,1,(a,b) -> a + b);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    public void destroy(){
        executor.shutdown();
    }

}
