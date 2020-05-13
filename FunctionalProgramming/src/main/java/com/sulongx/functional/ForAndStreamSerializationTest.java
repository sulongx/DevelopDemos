package com.sulongx.functional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * For循环和Stream流 串行化处理
 *
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-20
 */
public class ForAndStreamSerializationTest {

    public static void main(String[] args) {
        //intTest(1000000);
        //arrayListTest(1000000);
        //intTest(1000000);
//        for(int i = 0;i < 100;i ++){
//            arrayListTest(1000000);
//        }

        arrayListTest(1000000);
        System.out.println(resultInts.toString());
        System.out.println(resultStream.toString());
    }


    private static List<Long> resultInts = new ArrayList<>();

    private static List<Long> resultStream = new ArrayList<>();

    public static void arrayListTest(int len){
        System.out.println("-----------------使用集合对象测试----------------------");
        long start = 0L;
        long end = 0L;

        long result =  0L;

        //生成集合
        ArrayList<Integer> arrayList = createArrays(len);

        //使用for循环处理
        start = System.currentTimeMillis();
        for(int i = 0;i < arrayList.size();i ++){
            if(arrayList.get(i) > 1){
                result += arrayList.get(i);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("使用for循环处理消耗时间(ms): " + (end - start));
        resultInts.add(result);

        //使用Stream流处理
        start = System.currentTimeMillis();
        result = arrayList.stream()
                .reduce(0, (acc, value) -> (acc + value))
                .longValue();
        end = System.currentTimeMillis();
        System.out.println("使用Stream流处理消耗时间(ms): " + (end - start));
        resultStream.add(result);
    }


    /**
     * 基本类型测试
     */
    private static void intTest(int len){
        System.out.println("----------------使用基本类型测试----------------------");
        long start = 0L;
        long end = 0L;
        //生成数组
        int[] intArray = createInts(len);

        //使用for循环处理
        start = System.currentTimeMillis();
        for(int i = 0;i < intArray.length;i ++){
            if(intArray[i] > 1){
                Math.sqrt(intArray[i]);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("使用for循环处理消耗时间(ms): " + (end - start));

        //使用Stream流处理
        start = System.currentTimeMillis();
        Arrays.stream(intArray)
                .filter(value -> {
                    Math.sqrt(value);
                    return true;
                });
        end = System.currentTimeMillis();
        System.out.println("使用Stream流处理消耗时间(ms): " + (end - start));
    }



    /**
     * 创建基本类型数组
     * @return
     */
    private static int[] createInts(int len){
        int[] array = new int[len];
        for(int i = 0;i < len;i ++){
            array[i] = new Random().nextInt(len);
        }
        return array;
    }

    public static ArrayList<Integer> createArrays(int len){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0;i < len;i ++){
            arrayList.add(new Random().nextInt(len));
        }
        return arrayList;
    }

}
