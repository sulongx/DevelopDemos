package com.sulongx.actuator.k.demo01;

import com.sulongx.actuator.k.common.Distance;
import com.sulongx.actuator.k.common.EuclideanDistanceCalculator;
import com.sulongx.actuator.k.common.Sample;

import java.util.*;

/**
 * @author Sulongx
 */
public class knnClassifier {
    private final List<? extends Sample> dataSet;

    private Integer k;

    public knnClassifier(List<? extends Sample> dataSet, Integer k) {
        this.dataSet = dataSet;
        this.k = k;
    }

    public String classify(Sample example){
        Distance[] distances = new Distance[dataSet.size()];
        Integer index = 0;
        for(Sample realExample : dataSet){
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(realExample,example));
            index ++;
        }
        Arrays.sort(distances);
        Map<String,Integer> results = new HashMap<>();
        for(int i = 0;i < k;i ++){
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag,1,(a,b) -> a + b);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
