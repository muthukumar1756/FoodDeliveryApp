package com.swiggy.dao.impl;

import com.swiggy.customexception.FoodCreationException;
import com.swiggy.customexception.MenuCardNotFoundException;
import com.swiggy.customexception.RestaurantCreationException;
import com.swiggy.dao.RestaurantDAO;
import com.swiggy.database.DataBaseConnection;
import com.swiggy.model.Food;
import com.swiggy.model.FoodType;
import com.swiggy.model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Implements the data base service of the restaurant related operation
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class RestaurantDAOImpl implements RestaurantDAO {

    private static RestaurantDAO restaurantDAO;

    private final Connection connection;

    private RestaurantDAOImpl() {
        connection = DataBaseConnection.getConnection();
    }

    /**
     * <p>
     * Gets the object of the restaurant database implementation class.
     * </p>
     *
     * @return The restaurant database service implementation object
     */
    public static RestaurantDAO getInstance() {
        if (null == restaurantDAO) {
            return restaurantDAO = new RestaurantDAOImpl();
        }

        return restaurantDAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurants Represents all the {@link Restaurant}
     */
    @Override
    public boolean createRestaurants(final Map<Integer, Restaurant> restaurants) {
        for (final Restaurant restaurant : restaurants.values()) {

            if (!isRestaurantExist(restaurant)) {
                final String query = "insert into restaurant (name) values (?) returning id";
                final String restaurantName = restaurant.getName();

                try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, restaurantName);
                    final ResultSet resultSet = preparedStatement.executeQuery();

                    resultSet.next();
                    final int restaurantId = resultSet.getInt(1);

                    restaurant.setRestaurantId(restaurantId);
                } catch (SQLException message) {
                    throw new RestaurantCreationException(message.getMessage());
                }
            }
        }
        return true;
    }

    public boolean isRestaurantExist(final Restaurant restaurant) {
        final String checkQuery = "select count(*) from restaurant where name = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setString(1, restaurant.getName());
            final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            final int result = resultSet.getInt(1);

            if (0 < result) {
                return true;
            }
        } catch (SQLException message) {
            throw new RestaurantCreationException(message.getMessage());
        }

        return false;
    }

//    public static void main(String[] args) {
//        final Map<Integer, Restaurant> restaurants = new HashMap<>();
//        Restaurant restaurant = new Restaurant("nila");
//        restaurant.setRestaurantId(208);
//        restaurants.put(175 , restaurant);
//        new RestaurantDAOImpl().createRestaurants(restaurants);
//    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the current {@link Restaurant}
     */
    @Override
    public void createFood(final Food food, final Restaurant restaurant) {
        if (!isFoodExist(food)) {
            try {
                connection.setAutoCommit(false);
                final String query = "insert into food(name, rate, food_type, food_quantity) values(?, ?, ?, ?)returning id";

                try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, food.getFoodName());
                    preparedStatement.setFloat(2, food.getRate());
                    preparedStatement.setInt(3, FoodType.getId(food.getType()));
                    preparedStatement.setInt(4, food.getFoodQuantity());
                    final ResultSet resultSet = preparedStatement.executeQuery();

                    resultSet.next();
                    final int foodId = resultSet.getInt(1);

                    food.setFoodId(foodId);

                    if (createRestaurantFood(food, restaurant)) {
                        connection.commit();
                        connection.setAutoCommit(true);
                    }
                }
            } catch (SQLException message) {
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    throw new FoodCreationException(exception.getMessage());
                }
                throw new FoodCreationException(message.getMessage());
            }
        }
    }

    public boolean isFoodExist(final Food food) {
        final String checkQuery = "select count(*) from food where name = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setString(1, food.getFoodName());
            final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            final int result = resultSet.getInt(1);

            if (result > 0) {
                return true;
            }
        } catch (SQLException message) {
            throw new FoodCreationException(message.getMessage());
        }
        return false;
    }

    /**
     * <p>
     * Maps the food with restaurant.
     * </p>
     *
     * @param food Represents the current {@link Food}
     * @param restaurant Represents the id of the current {@link Restaurant}
     */
    private boolean createRestaurantFood(final Food food, final Restaurant restaurant) {
        final String query = "insert into restaurant_food (food_id, restaurant_id) values(?, ?)";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, food.getFoodId());
            preparedStatement.setInt(2, restaurant.getRestaurantId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException message) {
            throw new FoodCreationException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The map having all the restaurants.
     */
    @Override
    public Map<Integer, Restaurant> getRestaurants() {
        final Map<Integer, Restaurant> restaurants = new HashMap<>();
        final String query = "select id, name from restaurant";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                final Restaurant restaurant = new Restaurant(name);

                restaurant.setRestaurantId(id);
                restaurants.put(id, restaurant);
            }

            return restaurants;
        } catch (SQLException message) {
            throw new FoodCreationException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurant Represents the current {@link Restaurant}
     * @return The menucard list
     */
    @Override
    public List<Food> getMenuCard(final Restaurant restaurant) {
        final String query = """
        select f.id, f.name, f.rate, f.food_type, f.food_quantity from food f
        join restaurant_food rf on f.id = rf.food_id join restaurant r on rf.restaurant_id = r.id where r.id = ?
        """;

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, restaurant.getRestaurantId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                final float rate = resultSet.getFloat(3);
                final int foodType = resultSet.getInt(4);
                final int quantity = resultSet.getInt(5);

                if (foodType == 2) {
                    final Food food = new Food(name, rate, FoodType.NONVEG, quantity);

                    food.setFoodId(id);
                    restaurant.createNonVegMenucard(food);
                } else {
                    final Food food = new Food(name, rate, FoodType.VEG, quantity);

                    food.setFoodId(id);
                    restaurant.createVegMenucard(food);
                }
            }
            restaurant.createMenuCard();

            return restaurant.getMenuCard();
        } catch (SQLException message) {
            throw new MenuCardNotFoundException(message.getMessage());
        }
    }
}
