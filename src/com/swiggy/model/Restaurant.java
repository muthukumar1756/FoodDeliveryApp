package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static int idCount;
    private final String name;
    private final int id;
    private final List<Food> menuCard = new ArrayList<>();
    private final List<Food> vegMenuCard = new ArrayList<>();
    private final List<Food> nonVegMenuCard = new ArrayList<>();

    public Restaurant(final String name) {
        this.name = name;
        this.id = ++idCount;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setMenuCard() {
        menuCard.addAll(vegMenuCard);
        menuCard.addAll(nonVegMenuCard);
    }

    public void setVegMenuCard(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity) {
        vegMenuCard.add(new Food(name, rate, type, isVeg, foodQuantity));
    }

    public void setNonVegMenuCard(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity) {
        nonVegMenuCard.add(new Food(name, rate, type, isVeg, foodQuantity));
    }

    public List<Food> getVegMenuCard(){
        return vegMenuCard;
    }

    public List<Food> getNonVegMenuCard(){
        return nonVegMenuCard;
    }

    public List<Food> getMenuCard(){
        return menuCard;
    }
}
