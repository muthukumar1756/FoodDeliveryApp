package com.swiggy.model;

public class Cart {

    private final String NAME;
    private final int rate;
    private final String type;

    Cart(final String name, final int rate, final String type){
        this.NAME = name;
        this.rate = rate;
        this.type = type;
    }

    public String getName() {
        return NAME;
    }

    public int getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }
}