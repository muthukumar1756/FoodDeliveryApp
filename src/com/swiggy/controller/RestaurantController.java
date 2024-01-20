package com.swiggy.controller;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;
import com.swiggy.service.impl2.RestaurantServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Handles the restaurant related operation and responsible for receiving user input and processing it
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class RestaurantController {

    private static RestaurantController restaurantController;

    private final RestaurantService restaurantService;

    private RestaurantController() {
        restaurantService = RestaurantServiceImpl.getInstance();
    }

    /**
     * <p>
     * Gets the restaurant controller object.
     * </p>
     *
     * @return The restaurant controller object
     */
    public static RestaurantController getInstance() {
        if (null == restaurantController) {
            restaurantController = new RestaurantController();
        }

        return restaurantController;
    }

    /**
     * <p>
     * Gets all the restaurants
     * </p>
     *
     * @return The map having all the restaurants.
     */
    public Map<Integer, Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    /**
     * <p>
     * Creates all the restaurants.
     * </p>
     *
     * @param restaurants Represents all the {@link Restaurant}
     */
    public boolean createRestaurants(final Map<Integer, Restaurant> restaurants) {
        return restaurantService.createRestaurants(restaurants);
    }

    /**
     * <p>
     * Creates all the veg foods.
     * </p>
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    public void createVegFood(final Food food, final Restaurant restaurant) {
        restaurantService.createVegFood(food, restaurant);
    }

    /**
     * <p>
     * Creates all the nonveg foods.
     * </p>
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    public void createNonVegFood(final Food food, final Restaurant restaurant) {
        restaurantService.createNonVegFood(food, restaurant);
    }

    /**
     * <p>
     * Gets the menucard of the selected restaurant by the user.
     * </p>
     *
     * @param restaurant Represents the current {@link Restaurant}
     * @return The menucard list
     */
    public List<Food> getMenuCard(final Restaurant restaurant) {
        return restaurantService.getMenuCard(restaurant);
    }
}
