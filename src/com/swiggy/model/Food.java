package com.swiggy.model;

public class Food {

    private final String name;
    private final int rate;
    private final String type;
    private final boolean isVeg;
    private final int foodQuantity;

    public Food(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity) {
        this.name = name;
        this.rate = rate;
        this.type = type;
        this.isVeg = isVeg;
        this.foodQuantity = foodQuantity;
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

    public boolean isVeg() {
        return isVeg;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }
}