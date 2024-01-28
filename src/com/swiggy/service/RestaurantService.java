package com.swiggy.service;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

    /**
     * <p>
     * Creates all the restaurants.
     * </p>
     *
     * @param restaurants Represents all the {@link Restaurant}
     */
    boolean loadRestaurants(final Map<Integer, Restaurant> restaurants);

    /**
     * <p>
     * Creates the menucard for the restaurant.
     * </p>
     *
     * @param menuCard Contains the list of foods in the restaurant
     */
    void loadMenuCard(final Map<Food, Restaurant> menuCard);

    /**
     * <p>
     * Gets all the restaurants
     * </p>
     *
     * @return The map having all the restaurants.
     */
    Map<Integer, Restaurant> getRestaurants();

    /**
     * <p>
     * Gets the available food quantity in the restaurant .
     * </p>
     *
     * @param food Represents the current {@link Food} selected by the user
     * @param quantity Represents the quantity of the food given by the current user
     * @return Available quantity from the selected restaurant
     */
    int getQuantity(final Food food, final int quantity);

    /**
     * <p>
     * Gets the menucard of the selected restaurant by the user.
     * </p>
     *
     * @param restaurant Represents the current {@link Restaurant}
     * @return The menucard list
     */
    List<Food> getMenuCard(final Restaurant restaurant);
}
