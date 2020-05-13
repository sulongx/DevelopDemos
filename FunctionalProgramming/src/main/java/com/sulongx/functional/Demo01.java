package com.sulongx.functional;



import jdk.nashorn.internal.codegen.MapCreator;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-03-26
 */
public class Demo01 {
    public static void main(String[] args) {

//
//        Runnable task = () -> System.out.println("老铁双击666");
//
//        ActionListener listener = e -> System.out.println("老铁双击999");
//
//        Runnable task2 = () -> {
//            System.out.println("老铁双击888");
//            System.out.println("老铁双击888");
//        };
//
//
//
//        BinaryOperator<Integer> add = (x, y) -> add(x,y);
//
//        Integer apply = add.apply(10, 20);
//        System.out.println(apply);
//
//        BinaryOperator<Long> sub = (Long x,Long y) -> x - y;
//
//        List<String> nameList = new ArrayList<>();
//        nameList.add("张三");
//        nameList.add("李四");
//        nameList.add("王二");
//        nameList.add("麻子");
//        nameList.forEach(name ->{
//            if(name.equals("张三")){
//                System.out.println("张三是个罪犯");
//            }
//        });
//
//        Stream<String> stringStream = nameList.stream().filter(values -> {
//            System.out.println(values);
//            return false;
//        });
//
//        List<String> stringList = Stream.of("a", "b", "c").filter(value -> {
//            if(value.equals("a") || value.equals("b")){
//                return true;
//            }
//            return false;
//        }).collect(Collectors.toList());
//        System.out.println(stringList);


//        List<String> resultList = Stream.of(Arrays.asList(new String[]{"1", "2"}), Arrays.asList(new String[]{"3", "4"}))
//                .flatMap(items -> {
//                    System.out.println(items);
//                    ArrayList<String> newItems = new ArrayList<>();
//                    newItems.add("1");
//                    newItems.add("2");
//                    newItems.add("3");
//                    newItems.add("4");
//                    return newItems.stream();
//                })
//                .collect(Collectors.toList());
//
//        System.out.println(resultList);


//        ArrayList<Integer> numberList = new ArrayList<>();
//        numberList.add(1);
//        numberList.add(3);
//        numberList.add(4);
//        numberList.add(7);
//        numberList.add(2);
//        Integer result = numberList.stream()
//                .max(Comparator.comparingInt(num -> num.intValue()))
//                .get();
//        System.out.println(result);
//
//        Integer counts = Stream.of(1, 2, 3, 4)
//                .reduce(0, (num1, num2) -> num1 + num2);
//
//        System.out.println(counts);
//        BinaryOperator<Integer> add = (acc,num)-> acc + num;
//        Integer counts = add.apply(
//                add.apply(
//                        add.apply(0, 1), 2),
//                3);
//        System.out.println(counts);
//
//        Optional<String> value = Optional.of("???");
//        System.out.println(value.isPresent());

    }



    private static Integer  add(Integer t1,Integer t2){
        return t1 + t2;
    }
}
