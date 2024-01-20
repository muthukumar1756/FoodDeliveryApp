package com.swiggy.dao.impl;

import com.swiggy.customexception.PlaceOrderException;
import com.swiggy.dao.OrderDAO;
import com.swiggy.database.DataBaseConnection;
import com.swiggy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Implements the data base service of the order related operation
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class OrderDAOImpl implements OrderDAO {

    private static OrderDAO orderDAO;

    private final Connection connection;

    private OrderDAOImpl() {
        connection = DataBaseConnection.getConnection();
    }

    /**
     * <p>
     * Gets the object of the order database implementation class.
     * </p>
     *
     * @return The order database service implementation object
     */
    public static OrderDAO getInstance(){
        if (null == orderDAO) {
            orderDAO = new OrderDAOImpl();
        }

        return orderDAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return True if the order is placed, false otherwise
     */
    @Override
    public boolean placeOrder(final User user) {
        final String query = "select uc.id from user_cart uc where uc.user_id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int userCartId = resultSet.getInt(1);
                updateOrder(user, userCartId);
            }
            return true;
        } catch (SQLException message) {
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
    public void updateOrder(final User user, final int userCartId) {
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
            throw new PlaceOrderException(message.getMessage());
        }
    }
}
