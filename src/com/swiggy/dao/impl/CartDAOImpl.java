package com.swiggy.dao.impl;

import com.swiggy.customexception.CartNotFoundException;
import com.swiggy.customexception.CartUpdateException;
import com.swiggy.customexception.FoodCreationException;
import com.swiggy.dao.CartDAO;
import com.swiggy.database.DataBaseConnection;
import com.swiggy.model.Food;
import com.swiggy.model.FoodType;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implements the data base service of the cart related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class CartDAOImpl implements CartDAO {

    private static CartDAO cartDAO;

    private final Connection connection;

    private CartDAOImpl() {
        connection = DataBaseConnection.getConnection();
    }

    /**
     * <p>
     * Gets the object of the cart database implementation class.
     * </p>
     *
     * @return The cart database service implementation object
     */
    public static CartDAO getInstance() {
        if (null == cartDAO) {
            return cartDAO = new CartDAOImpl();
        }

        return cartDAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food} selected by the user
     * @param user Represents the current {@link User}
     * @param quantity Represents the quantity of the {@link Food} given by the current user
     * @param restaurantId Represents the id of the current {@link Restaurant}
     * @return True if the food is added to the user cart, false otherwise
     */
    @Override
    public boolean addFoodToCart(final Food food, final User user, final int quantity, final int restaurantId) {
        final String query = "insert into user_cart_mapping (food_id, quantity) values (?, ?) returning id";

        try {
            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, food.getFoodId());
                preparedStatement.setInt(2, quantity);
                final ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                final int cartId = resultSet.getInt(1);

                if (updateCart(user, cartId, restaurantId)) {
                    connection.commit();
                    connection.setAutoCommit(true);

                    return true;
                }
            }
        } catch (SQLException message) {
            try {
                connection.rollback();
            } catch (SQLException cartMessage) {
                throw new CartUpdateException(cartMessage.getMessage());
            }
            throw new CartUpdateException(message.getMessage());
        }

        return false;
    }

    /**
     * <p>
     * Maps the cart id with the user cart.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param cartId Represents the id of the current user cart
     * @param restaurantId Represents the id of the selected restaurant
     * @return True if the food is added to the user cart, false otherwise
     */
    public boolean updateCart(final User user, final int cartId, final int restaurantId) {
        final String query = "insert into user_cart (user_id, restaurant_id, cart_id) values (?, ?, ?)";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, restaurantId);
            preparedStatement.setInt(3, cartId);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException message) {
            throw new FoodCreationException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return The map having all the foods from the user cart
     */
    @Override
    public Map<Food, Integer> getCart(final User user) {
        final Map<Food, Integer> userCart = new HashMap<>();
        final String query = """
                select f.id, f.name, f.rate, f.food_type, cm.quantity from food f
                join user_cart_mapping cm on f.id = cm.food_id join user_cart uc on cm.id = uc.cart_id
                join users u on uc.user_id = u.id where u.id = ?
                """;

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                final float rate = resultSet.getFloat(3);
                final int foodType = resultSet.getInt(4);
                final int quantity = resultSet.getInt(5);

                final Food food;

                if (foodType == 2) {
                    food = new Food(name, rate, FoodType.NONVEG, quantity);
                } else {
                    food = new Food(name, rate, FoodType.VEG, quantity);
                }
                food.setFoodId(id);
                userCart.put(food, quantity);
            }

          return userCart;
        } catch (SQLException message) {
            throw new CartNotFoundException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param food Represents the current {@link Food} selected by the user
     * @return True if the food is removed,false otherwise
     */
    @Override
    public boolean removeFood(final User user, final Food food) {
        final String query = """
                delete from user_cart_mapping where id in(
                select cm.id from user_cart_mapping cm join food f on cm.food_id = f.id
                join user_cart uc on cm.id = uc.cart_id join users u on uc.user_id = u.id
                where u.id = ? and f.id = ?)""";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, food.getFoodId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException message) {
            throw new CartUpdateException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return The true if the cart is cleared, false otherwise
     */
    @Override
    public boolean clearCart(final User user) {
        final String query = "delete from user_cart uc where user_id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());

            return true;
        } catch (SQLException message) {
            throw new CartUpdateException(message.getMessage());
        }
    }
}
