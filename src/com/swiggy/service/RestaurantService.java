package com.swiggy.service;

import com.swiggy.model.Restaurant;

import java.util.Map;

public interface RestaurantService {

    Map<Integer, Restaurant> getRestaurants();

    void setRestaurantDetails(final Integer restaurantId, final Restaurant restaurant);

    void setFood(final String name, final int rate, final String type, final boolean isVeg, final int foodQuantity, final Restaurant restaurant);
}
