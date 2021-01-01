package com.sulongx.util.data;


import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.common.collect.Lists;
import com.sulongx.util.data.test.model.EntityA;
import com.sulongx.util.data.test.model.EntityB;

/**
 * 多表数据封装(一对多)
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2020/12/29 15:03
 */
public class MultiListDataEn<T, R> {

    private final List<T> targets;

    private final List<R> resources;

    private Map<String, List<R>> resourceMap;

    private final Map<String, Method> methodMapTarget = new HashMap<>(16);

    private final Map<String, Method> methodMapResource = new HashMap<>(16);

    private MultiListDataEn(List<T> targets, List<R> resources){
        this.targets = targets;
        this.resources = resources;

        setMethodToMap(targets.get(0).getClass().getDeclaredMethods(), methodMapTarget);
        setMethodToMap(resources.get(0).getClass().getDeclaredMethods(), methodMapResource);
    }


    /**
     * 设置双方数据源
     * @param targets
     * @param resources
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R>  MultiListDataEn<T, R> setData(List<T> targets, List<R> resources){
        if(targets == null || targets.size() == 0 || resources == null || resources.size() == 0){
            throw new RuntimeException("parameter must not null");
        }
        return new MultiListDataEn<T, R>(targets, resources);
    }

    /**
     * 设置关联值
     * @param function
     * @return
     */
    public MultiListDataEn<T, R> setDataORM(Function<R, ?> function){
        resourceMap = resources.stream()
                .filter(r ->
                        function.apply(r) != null && !"".equals(function.apply(r))
                ).collect(Collectors.toMap(r -> function.apply(r).toString(),
                        Lists::newArrayList,
                    (List<R> newList, List<R> oldList) -> {
                        oldList.addAll(newList);
                        return oldList;
                }));

        return this;
    }

    /**
     * 封装数据
     * @param function
     * @param filedNames
     */
    public void run(Function<? super T, ?> function, String... filedNames){
        targets.forEach(t ->
            resourceMap.get(function.apply(t).toString()).forEach(r -> {
                for(String filedName : filedNames){
                    try {
                        methodMapResource.get("set" + captureName(filedName)).invoke(r, methodMapTarget.get("get" + captureName(filedName)).invoke(t));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            })
        );
    }


    private void setMethodToMap(Method[] methods, Map<String, Method> methodMap){
        for(Method method : methods){
            methodMap.put(method.getName(), method);
        }
    }

    private String captureName(String str) {
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }


    public static void main(String[] args) {
        //人员
        EntityA a = new EntityA();
        a.setId(1);
        a.setName("张三");
        a.setAge(20);

        EntityA a1 = new EntityA();
        a1.setId(2);
        a1.setName("李四");
        a1.setAge(21);

        //业务记录
        EntityB b = new EntityB();
        b.setId(1);
        b.setaId(1);

        EntityB b1 = new EntityB();
        b1.setId(2);
        b1.setaId(1);

        EntityB b2 = new EntityB();
        b2.setId(3);
        b2.setaId(2);

        EntityB b3 = new EntityB();
        b3.setId(4);
        b3.setaId(1);

        EntityB b4 = new EntityB();
        b4.setId(1);


        //封装模拟数据
        List<EntityA> aEntityList = new ArrayList<>();
        aEntityList.add(a);
        aEntityList.add(a1);

        List<EntityB> bEntityList = new ArrayList<>();
        bEntityList.add(b);
        bEntityList.add(b1);
        bEntityList.add(b2);
        bEntityList.add(b3);
        bEntityList.add(b4);

        //测试数据封装工具类
        MultiListDataEn.setData(aEntityList, bEntityList)
                .setDataORM(EntityB::getaId)
                .run(EntityA::getId, "name", "age");

        System.out.println(bEntityList);


    }

}
