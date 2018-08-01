package com.zcx.model;

/**
 * @author: Ant
 * @Date: 2018/08/01 10:19
 * @Description: 消费分类实体类
 */
public class Category {
    private String category_id;
    private String category;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Category{" +
                "category_id='" + category_id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
