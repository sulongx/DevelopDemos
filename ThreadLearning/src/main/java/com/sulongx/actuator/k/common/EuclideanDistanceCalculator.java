package com.sulongx.actuator.k.common;

/**
 * @author Sulongx
 */
public class EuclideanDistanceCalculator {
    public static double calculate(Sample example1,Sample example2){
        double res = 0.0D;
        double[] data1 = example1.getExample();
        double[] data2 = example2.getExample();
        if(data1.length != data2.length){
            throw new IllegalArgumentException("this params don't have same length!");
        }
        for(int i = 0;i < data1.length;i ++){
            res += Math.pow(data1[i] - data2[i],2);
        }
        return Math.sqrt(res);
    }
}
