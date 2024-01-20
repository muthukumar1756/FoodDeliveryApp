package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Represents restaurant entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class Restaurant {

    private final String name;
    private final List<Food> menuCard;
    private final List<Food> vegMenuCard;
    private final List<Food> nonVegMenuCard;

    private int id;

    public Restaurant(final String name) {
        this.name = name;
        this.menuCard = new ArrayList<>();
        this.vegMenuCard = new ArrayList<>();
        this.nonVegMenuCard = new ArrayList<>();
    }

    public void setRestaurantId(final int id) {
        this.id = id;
    }

    public void createVegMenucard(final Food food) {
        vegMenuCard.add(food);
    }

    public void createNonVegMenucard(final Food food) {
        nonVegMenuCard.add(food);
    }

    public void createMenuCard() {
        menuCard.addAll(vegMenuCard);
        menuCard.addAll(nonVegMenuCard);
    }

    public String getName() {
        return name;
    }

    public int getRestaurantId() {
        return id;
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