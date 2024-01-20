package com.swiggy.service.impl;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Implements the service of the restaurant related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class RestaurantServiceImpl implements RestaurantService {

    private static RestaurantService restaurantService;

    public final Map<Integer, Restaurant> restaurants = new HashMap<>();

    private RestaurantServiceImpl () {
    }

    /**
     * <p>
     * Gets the restaurant service implementation object.
     * </p>
     *
     * @return The restaurant service implementation object
     */
    public static RestaurantService getInstance() {
        if (null == restaurantService) {
            restaurantService = new RestaurantServiceImpl();
        }

        return restaurantService;
    }

    /**
     * {@inheritDoc}
     *
     * @return The map having all the restaurants
     */
    @Override
    public Map<Integer, Restaurant> getRestaurants(){
        return restaurants;
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurantMap Represents all the {@link Restaurant}
     */
    @Override
    public boolean createRestaurants(final Map<Integer, Restaurant> restaurantMap) {
        restaurants.putAll(restaurantMap);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    @Override
    public void createVegFood(final Food food, final Restaurant restaurant) {
        restaurant.createVegMenucard(food);
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    @Override
    public void createNonVegFood(final Food food, final Restaurant restaurant) {
        restaurant.createNonVegMenucard(food);
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurant Represents the current {@link Restaurant}
     * @return The menucard list
     */
    @Override
    public List<Food> getMenuCard(final Restaurant restaurant) {
        return restaurant.getMenuCard();
    }
}
