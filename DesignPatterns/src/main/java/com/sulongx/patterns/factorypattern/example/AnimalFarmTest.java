package com.sulongx.patterns.factorypattern.example;

/**
 * 描述:
 * 工厂方法模式设计畜牧场
 *
 * @author xiongsulong
 * @create 2020-10-28 15:38
 */
public class AnimalFarmTest {
    public static void main(String[] args) {
        try {
            Animal animal;
            AnimalFarm farm;
            farm = (AnimalFarm) ReadXML2.getObject();
            animal = farm.newAnimal();
            animal.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
