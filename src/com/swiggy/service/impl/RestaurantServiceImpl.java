package com.swiggy.service.impl;

import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;

import java.util.HashMap;
import java.util.Map;

public class RestaurantServiceImpl implements RestaurantService {

    private static RestaurantService instance;

    public static Map<Integer, Restaurant> restaurants = new HashMap<>();

    private RestaurantServiceImpl () {
    }

    public static RestaurantService getInstance() {
        if (instance ==  null) {
            instance = new RestaurantServiceImpl();
        }

        return instance;
    }

    public Map<Integer, Restaurant> getRestaurants(){
        return restaurants;
    }

    public void setRestaurantDetails (final Integer restaurantId, final Restaurant restaurant) {
        restaurants.put(restaurantId, restaurant);
    }

    public void setFood(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity, final Restaurant restaurant) {
        if (isVeg) {
            restaurant.setVegMenuCard(name, rate, type, isVeg, foodQuantity);
        } else {
            restaurant.setNonVegMenuCard(name, rate, type, isVeg, foodQuantity);
        }
    }
}
