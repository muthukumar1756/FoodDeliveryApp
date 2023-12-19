package com.swiggy.model;

public class Food {

    private final int FOOD_ID;
    private static int foodIdCount;
    private final String NAME;
    private int rate;
    private String type;
    private boolean isVeg;
    private int foodQuantity;

    public Food(String name, int rate, String type, boolean isVeg) {
        this.NAME = name;
        this.rate = rate;
        this.type = type;
        this.FOOD_ID = ++foodIdCount;
        this.isVeg = isVeg;
    }
    public Food(String name, int rate, String type, boolean isVeg, int foodQuantity) {
        this.NAME = name;
        this.rate = rate;
        this.type = type;
        this.FOOD_ID = ++foodIdCount;
        this.isVeg = isVeg;
        this.foodQuantity = foodQuantity;
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

    public int getFoodId() {
        return FOOD_ID;
    }

    public boolean isVeg() {
        return isVeg;
    }
}