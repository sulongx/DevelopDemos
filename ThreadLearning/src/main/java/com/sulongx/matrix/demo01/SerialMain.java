package com.sulongx.matrix.demo01;

import com.sulongx.matrix.MatrixGenerator;

/**
 * @author Sulongx
 */
public class SerialMain {
    public static void main(String[] args) {
        Long startTime = 0L;
        Long endTime = 0L;
        double[][] matrix1 = MatrixGenerator.generate(2000,2000);
        double[][] matrix2 = MatrixGenerator.generate(2000,2000);

        double[][] result = new double[matrix1.length][matrix2[0].length];

        System.out.println("开始...");
        startTime = System.currentTimeMillis();
        SerialMultiplier.multiply(matrix1,matrix2,result);
        endTime = System.currentTimeMillis();
        System.out.println("结束.");
        System.out.println("总共消耗时间：" + (endTime - startTime) + " ms");
    }
}
