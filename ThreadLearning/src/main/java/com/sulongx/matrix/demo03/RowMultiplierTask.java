package com.sulongx.matrix.demo03;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @author Sulongx
 */
public class RowMultiplierTask implements Runnable {

    private final double[][] result;

    private final double[][] matrix1;
    private final double[][] matrix2;

    private final int row;

    public RowMultiplierTask(double[][] result,double[][] matrix1,double[][] matrix2,int row){
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }

    @Override
    public void run() {
    }
}
