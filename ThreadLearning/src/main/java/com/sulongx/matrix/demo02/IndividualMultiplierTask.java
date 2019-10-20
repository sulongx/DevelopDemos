package com.sulongx.matrix.demo02;

/**
 * @author Sulongx
 */
public class IndividualMultiplierTask implements Runnable{
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;

    private final int rows;
    private final int columns;

    public IndividualMultiplierTask(double[][] result,double[][] matrix1,double[][] matrix2,int rows,int columns){
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.rows = rows;
        this.columns = columns;
    }


    @Override
    public void run() {
        result[rows][columns] = 0;
        for(int k = 0;k < matrix1[rows].length;k ++){
            result[rows][columns] += matrix1[rows][k] * matrix2[k][columns];
        }
    }
}
