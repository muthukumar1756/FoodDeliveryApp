package com.swiggy.controller;

import com.swiggy.model.User;
import com.swiggy.model.Food;

public class CartController {

    private static CartController instance;

    private CartController () {
    }

    public static CartController getInstance() {
        if (instance == null) {
            instance = new CartController();
        }

        return instance;
    }

    public void addSelectedFoodToCart(final Food selectedFood, final User currentUser) {
        currentUser.addFoodToCart(selectedFood);
    }
}
