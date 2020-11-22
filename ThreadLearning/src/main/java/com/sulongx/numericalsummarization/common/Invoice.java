package com.sulongx.numericalsummarization.common;

/**
 * 描述:
 * 发票
 *
 * @author xiongsulong
 * @create 2020-11-02 10:49
 */
public class Invoice {

    private String id;
    private String customerId;
    private double ammount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }
}
