package com.swiggy.service.impl2;

import com.swiggy.dao.RestaurantDAO;
import com.swiggy.dao.impl.RestaurantDAOImpl;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;

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

    private final RestaurantDAO restaurantDAO;

    private RestaurantServiceImpl() {
        restaurantDAO = RestaurantDAOImpl.getInstance();
    }

    /**
     * <p>
     * Gets the restaurant service implementation object.
     * </p>
     *
     * @return The restaurant service implementation object
     */
    public static RestaurantService getInstance() {
        if (restaurantService ==  null) {
            restaurantService = new RestaurantServiceImpl();
        }

        return restaurantService;
    }

    /**
     * {@inheritDoc}
     *
     * @return The map having all the restaurants.
     */
    @Override
    public Map<Integer, Restaurant> getRestaurants() {
        return restaurantDAO.getRestaurants();
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurants Represents all the {@link Restaurant}
     */
    @Override
    public boolean createRestaurants(Map<Integer, Restaurant> restaurants) {
        return restaurantDAO.createRestaurants(restaurants);
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    @Override
    public void createVegFood(final Food food, final Restaurant restaurant) {
        restaurantDAO.createFood(food, restaurant);
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    @Override
    public void createNonVegFood(final Food food, final Restaurant restaurant) {
        restaurantDAO.createFood(food, restaurant);
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurant Represents the current {@link Restaurant}
     * @return The menucard list
     */
    @Override
    public List<Food> getMenuCard(Restaurant restaurant) {
        return restaurantDAO.getMenuCard(restaurant);
    }
}
