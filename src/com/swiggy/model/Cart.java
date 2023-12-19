package com.swiggy.model;

public class Cart {

    private String name;
    private int rate;
    private String type;

    Cart(String name, int rate, String type){
        this.name = name;
        this.rate = rate;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }
}