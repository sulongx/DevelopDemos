package com.sulongx.patterns.factorypattern.example;

/**
 * 描述:
 * 具体工厂：养牛场
 *
 * @author xiongsulong
 * @create 2020-10-28 16:00
 */
public class CattleFarm implements AnimalFarm {

    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生!");
        return new Cattle();
    }
}
