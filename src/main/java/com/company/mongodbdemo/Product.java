package com.company.mongodbdemo;

import java.util.Date;

public class Product {

    private String id;

    private  String name;

    private double price;

    private int totalCount;

    private int currentCount;

    private Category category;

    private Date date;

    private String description;

    public Product() {
    }

    public Product(String id, String name, double price, int totalCount, int currentCount, Category category, Date date, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public Product(String name, double price, int totalCount, int currentCount, Category category, Date date, String description) {
        this.name = name;
        this.price = price;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
