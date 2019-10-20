package com.sulongx.matrix;

import java.util.Random;

/**
 *
 * @author Sulongx
 */
public class MatrixGenerator {

    /**
     * 生成矩阵
     * @param rows
     * @param columns
     * @return
     */
    public static double[][] generate(int rows,int columns){
        double[][] res = new double[rows][columns];
        Random random = new Random();
        for(int i = 0;i < rows;i ++){
            for(int j = 0;j < columns;j ++){
                res[i][j] = random.nextDouble()*10;
            }
        }
        return res;
    }
}
