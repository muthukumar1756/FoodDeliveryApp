package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final String NAME;
    private final int ID;
    private static int idCount;

    private List<Food> menuCard = new ArrayList<>();
    private List<Food> vegMenuCard = new ArrayList<>();
    private List<Food> nonVegMenuCard = new ArrayList<>();

    public Restaurant(String NAME) {
        this.NAME = NAME;
        this.ID = ++idCount;
    }

    public String getName() {
        return NAME;
    }

    public int getId() {
        return ID;
    }

    public void setMenuCard() {
        menuCard.addAll(vegMenuCard);
        menuCard.addAll(nonVegMenuCard);
    }

    public void setVegMenuCard(String name, int rate, String type, boolean isVeg) {
        vegMenuCard.add(new Food(name, rate, type, isVeg));
    }

    public void setNonVegMenuCard(String name, int rate, String type, boolean isVeg) {
        nonVegMenuCard.add(new Food(name, rate, type, isVeg));
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
