package com.swiggy;

import com.swiggy.controller.RestaurantController;
import com.swiggy.view.UserView;

public class Main {
    private static final RestaurantInitialisation RESTAURANT_INITIALISATION_INSTANCE = RestaurantInitialisation.getInstance();
    public static void main(String[] args) {
        RESTAURANT_INITIALISATION_INSTANCE.intializeRestaurants();
        UserView.getInstance().printMenu();
    }
}
