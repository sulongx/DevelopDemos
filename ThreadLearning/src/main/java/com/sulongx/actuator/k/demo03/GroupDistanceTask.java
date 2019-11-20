package com.sulongx.actuator.k.demo03;

import com.sulongx.actuator.k.common.Distance;
import com.sulongx.actuator.k.common.EuclideanDistanceCalculator;
import com.sulongx.actuator.k.common.Sample;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Sulongx
 */
public class GroupDistanceTask implements Runnable {
    private final Distance[] distances;
    private final int startIndex,endIndex;
    private final Sample example;
    private final List<? extends Sample> dataSet;
    private final CountDownLatch endController;

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, Sample example, List<? extends Sample> dataSet, CountDownLatch endController) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.example = example;
        this.dataSet = dataSet;
        this.endController = endController;
    }

    @Override
    public void run() {
        for(int i = startIndex;i < endIndex;i ++){
            Sample localExample = dataSet.get(i);
            distances[i] = new Distance();
            distances[i].setIndex(i);
            distances[i].setDistance(EuclideanDistanceCalculator.calculate(localExample,example));
        }
        endController.countDown();
    }
}
