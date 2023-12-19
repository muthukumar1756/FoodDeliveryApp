package com.swiggy.controller;

import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;

import java.util.Map;

public class RestaurantController {
    private static RestaurantController instance;
    private static final RestaurantService RESTAURANT_SERVICE_INSTANCE = RestaurantService.getInstance();

    private RestaurantController() {
    }

    public static RestaurantController getInstance() {
        if (instance ==  null) {
            instance = new RestaurantController();
        }
        return instance;
    }

    public Map<Integer, Restaurant> getRestaurantsList(){
        return RESTAURANT_SERVICE_INSTANCE.getRestaurants();
    }

    public void setRestaurants(Integer restaurantId, Restaurant restaurant) {
        RESTAURANT_SERVICE_INSTANCE.setRestaurantDetails(restaurantId, restaurant);
    }

    public void setFoodToMenuCard(String name, int rate, String type, boolean isVeg, Restaurant restaurant) {
        RESTAURANT_SERVICE_INSTANCE.addFoodToMenucard(name, rate, type, isVeg, restaurant);
    }
}
