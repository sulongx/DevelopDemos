package com.sulongx.actuator.k.demo02;

import com.sulongx.actuator.k.common.Distance;
import com.sulongx.actuator.k.common.EuclideanDistanceCalculator;
import com.sulongx.actuator.k.common.Sample;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sulongx
 */
public class IndividualDistanceTask implements Runnable {

    private final Distance[] distances;
    private final Integer index;
    private final Sample localExample;
    private final Sample example;
    private final CountDownLatch endController;

    public IndividualDistanceTask(Distance[] distances, Integer index, Sample localExample, Sample example, CountDownLatch endController) {
        this.distances = distances;
        this.index = index;
        this.localExample = localExample;
        this.example = example;
        this.endController = endController;
    }

    @Override
    public void run() {
        distances[index] = new Distance();
        distances[index].setIndex(index);
        distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample,example));
        endController.countDown();
    }
}
