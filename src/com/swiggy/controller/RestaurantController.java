package com.swiggy.controller;

import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;
import com.swiggy.service.impl.RestaurantServiceImpl;

import java.util.Map;

public class RestaurantController {

    private static RestaurantController instance;
    private static final RestaurantService RESTAURANT_SERVICE = RestaurantServiceImpl.getInstance();

    private RestaurantController() {
    }

    public static RestaurantController getInstance() {
        if (instance ==  null) {
            instance = new RestaurantController();
        }
        return instance;
    }

    public Map<Integer, Restaurant> getRestaurantsList(){
        return RESTAURANT_SERVICE.getRestaurants();
    }

    public void setRestaurants(final Integer restaurantId, final Restaurant restaurant) {
        RESTAURANT_SERVICE.setRestaurantDetails(restaurantId, restaurant);
    }

    public void setFoodToMenuCard(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity, final Restaurant restaurant) {
        RESTAURANT_SERVICE.setFood(name, rate, type, isVeg, foodQuantity, restaurant);
    }
}
