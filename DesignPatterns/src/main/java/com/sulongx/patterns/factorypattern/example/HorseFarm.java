package com.sulongx.patterns.factorypattern.example;

/**
 * 描述:
 * 具体工厂：养马场
 *
 * @author xiongsulong
 * @create 2020-10-28 15:59
 */
public class HorseFarm implements AnimalFarm {

    @Override
    public Animal newAnimal() {
        System.out.println("新马出生!");
        return new Horse();
    }
}
