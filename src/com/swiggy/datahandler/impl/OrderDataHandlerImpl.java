package com.swiggy.datahandler.impl;

import com.swiggy.exception.CartUpdateException;
import com.swiggy.exception.PlaceOrderException;
import com.swiggy.datahandler.OrderDataHandler;
import com.swiggy.database.DataBaseConnection;
import com.swiggy.model.Food;
import com.swiggy.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * <p>
 * Implements the data base service of the order related operation
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class OrderDataHandlerImpl implements OrderDataHandler {

    private static OrderDataHandler orderDataHandler;

    private final Logger logger;
    private final Connection connection;

    private OrderDataHandlerImpl() {
        logger = Logger.getLogger(OrderDataHandlerImpl.class);
        connection = DataBaseConnection.getConnection();
    }

    /**
     * <p>
     * Gets the object of the order database implementation class.
     * </p>
     *
     * @return The order database service implementation object
     */
    public static OrderDataHandler getInstance(){
        if (null == orderDataHandler) {
            orderDataHandler = new OrderDataHandlerImpl();
        }

        return orderDataHandler;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return True if the order is placed, false otherwise
     */
    @Override
    public boolean placeOrder(final User user, final Map<Food, Integer> cart) {
        final String query = "select uc.id from user_cart uc where uc.user_id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int userCartId = resultSet.getInt(1);
                updateOrder(user, userCartId);
            }

            for (final Food food : cart.keySet()) {
                updateQuantity(food, food.getFoodQuantity());
            }

            return true;
        } catch (SQLException message) {
            logger.error(message.getMessage());
            throw new PlaceOrderException(message.getMessage());
        }
    }

    /**
     * <p>
     * Maps the order with user id.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param userCartId Represents the cart id belongs to the user
     */
    private void updateOrder(final User user, final int userCartId) {
        final String query = """
                insert into orders (user_id, user_cart_id, address, total_amount)values (?, ?, ?, (
                select sum(f.rate * cm.quantity) as total_amount from food f
                join user_cart_mapping cm on f.id = cm.food_id join user_cart uc on cm.id = uc.cart_id
                join users u on uc.user_id = u.id where u.id = ? and uc.id = ?))""";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, userCartId);
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.setInt(5, userCartId);
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            logger.error(message.getMessage());
            throw new PlaceOrderException(message.getMessage());
        }
    }

    /**
     * <p>
     * Updates the food quantity in restaurant after ordered by user.
     * </p>
     *
     * @param food Represents the current {@link Food} selected by the user
     * @param quantity quantity Represents the quantity of the food given by the current user
     */
    public void updateQuantity(final Food food, final int quantity) {
        final String query = "update food set food_quantity = food_quantity - ? where id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, food.getFoodId());
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            logger.error(message.getMessage());
            throw new CartUpdateException(message.getMessage());
        }
    }
}
