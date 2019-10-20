package com.sulongx.matrix.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sulongx
 */
public class ParallelIndividualMultiplier {
    public static void multiply(double[][] matrix1,double[][] matrix2,double[][] result){
        List<Thread> threads = new ArrayList<>();
        int rows = matrix1.length;
        int columns = matrix2.length;
        for(int i = 0;i < rows;i ++){
            for(int j = 0;j < columns;j ++){
                IndividualMultiplierTask multiplierTask = new IndividualMultiplierTask(result, matrix1, matrix2, rows, columns);
                Thread thread = new Thread(multiplierTask);
                thread.start();
                threads.add(thread);

                if(threads.size() % 10 == 0){
                    waitForThreads(threads);
                }
            }
        }
    }


    private static void waitForThreads(List<Thread> threads){
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }

}
