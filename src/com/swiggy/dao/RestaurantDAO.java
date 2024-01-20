package com.swiggy.dao;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Provides data base service for the restaurant
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface RestaurantDAO {

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
      * Creates all the foods.
      * </p>
      *
      * @param food Represents the current {@link Food}
      * @param restaurant Represents the current {@link Restaurant}
      */
     void createFood(final Food food, final Restaurant restaurant);

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
