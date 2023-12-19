package com.swiggy.service;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private static RestaurantService instance;

    public static Map<Integer, Restaurant> restaurants = new HashMap<>();

    private RestaurantService() {
    }

    public static RestaurantService getInstance() {
        if (instance ==  null) {
            instance = new RestaurantService();
        }

        return instance;
    }

    public Map<Integer, Restaurant> getRestaurants(){
        return restaurants;
    }

    public void setRestaurantDetails (Integer restaurantId, Restaurant restaurant) {
        restaurants.put(restaurantId, restaurant);
    }

    public void addFoodToMenucard(String name, int rate, String type, boolean isVeg, Restaurant restaurant) {
        if (isVeg) {
            restaurant.setVegMenuCard(name, rate, type, isVeg);
        } else {
            restaurant.setNonVegMenuCard(name, rate, type, isVeg);
        }
    }

}
