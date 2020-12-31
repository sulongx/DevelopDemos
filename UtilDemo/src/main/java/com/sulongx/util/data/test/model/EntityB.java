package com.sulongx.util.data.test.model;

/**
 * 业务员实体B
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2020/12/31 16:54
 */
public class EntityB {

    private Integer id;

    private Integer aId;

    /******业务添加字段*********/

    private String name;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EntityB{" +
                "id=" + id +
                ", aId=" + aId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
