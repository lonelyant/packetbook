package com.zcx.model;

/**
 * @author: Ant
 * @Date: 2018/08/01 09:53
 * @Description: 消费实体类
 */
public class Expenses {
    private String er_id;
    private String category;
    private String category_info;
    private double money;
    private String fromWho;


    public String getEr_id() {
        return er_id;
    }

    public void setEr_id(String er_id) {
        this.er_id = er_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_info() {
        return category_info;
    }

    public void setCategory_info(String category_info) {
        this.category_info = category_info;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "er_id='" + er_id + '\'' +
                ", category='" + category + '\'' +
                ", category_info='" + category_info + '\'' +
                ", money=" + money +
                ", fromWho='" + fromWho + '\'' +
                '}';
    }
}
