package com.sulongx.functional;



import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-03-26
 */
public class Demo01 {
    public static void main(String[] args) {


        Runnable task = () -> System.out.println("老铁双击666");

        ActionListener listener = e -> System.out.println("老铁双击999");

        Runnable task2 = () -> {
            System.out.println("老铁双击888");
            System.out.println("老铁双击888");
        };



        BinaryOperator<Integer> add = (x, y) -> add(x,y);

        Integer apply = add.apply(10, 20);
        System.out.println(apply);

        BinaryOperator<Long> sub = (Long x,Long y) -> x - y;

        List<String> nameList = new ArrayList<>();
        nameList.add("张三");
        nameList.add("李四");
        nameList.add("王二");
        nameList.add("麻子");
        nameList.forEach(name ->{
            if(name.equals("张三")){
                System.out.println("张三是个罪犯");
            }
        });

        Stream<String> stringStream = nameList.stream().filter(values -> {
            System.out.println(values);
            return false;
        });

        System.out.println(Arrays.asList(stringStream));

    }



    private static Integer  add(Integer t1,Integer t2){
        return t1 + t2;
    }
}
