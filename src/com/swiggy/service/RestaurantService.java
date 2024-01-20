package com.swiggy.service;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

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
     * Creates all the restaurants.
     * </p>
     *
     * @param restaurants Represents all the {@link Restaurant}
     */
    boolean createRestaurants(final Map<Integer, Restaurant> restaurants);

    /**
     * <p>
     * Creates all the veg foods.
     * </p>
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    void createVegFood(final Food food, final Restaurant restaurant);

    /**
     * <p>
     * Creates all the nonveg foods.
     * </p>
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    void createNonVegFood(final Food food, final Restaurant restaurant);

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
