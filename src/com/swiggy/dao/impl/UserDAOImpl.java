package com.swiggy.dao.impl;

import com.swiggy.customexception.UserCreationException;
import com.swiggy.customexception.UserNotFoundException;
import com.swiggy.customexception.UserUpdateException;
import com.swiggy.dao.UserDAO;
import com.swiggy.database.DataBaseConnection;
import com.swiggy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Implements the data base service of the user related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserDAOImpl implements UserDAO {

    private static UserDAO userDAO;

    private final Connection connection;

    private UserDAOImpl() {
        connection = DataBaseConnection.getConnection();
    }

    /**
     * <p>
     * Gets the object of the user database implementation class.
     * </p>
     *
     * @return The user database service implementation object
     */
    public static UserDAO getInstance() {
        if (null == userDAO) {
            return userDAO = new UserDAOImpl();
        }

        return userDAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return True if user is created, false otherwise
     */
    public boolean createUser(final User user) {
        final String query = """
                insert into users (name, phone_number, email_id, password) values (?, ?, ?, ?) returning id""";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getEmailId());
            preparedStatement.setString(4, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            final int user_id = resultSet.getInt(1);

            user.setId(user_id);

            return true;
        } catch (SQLException message) {
            throw new UserCreationException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @param password Represents the password of the current user
     * @return The current user
     */
    public User getUser(final String phoneNumber, final String password) {
        final String query = """
                select id, name, phone_number, email_id, password from users where phone_Number = ? and password = ?""";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, password);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final int userid = resultSet.getInt(1);
                final User user = new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));

                user.setId(userid);

                return user;
            } else {
                return null;
            }
        } catch (SQLException message) {
            throw new UserNotFoundException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @return True if the user is exist, false otherwise
     */
    @Override
    public boolean isUserExist(final String phoneNumber) {
        final String query = "select phone_number from users where phone_number = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException message) {
            throw new UserNotFoundException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param name Represents the name of the current user
     */
    @Override
    public void updateUserName(User user, String name) {
        final String query = "update users set name = ? where id = ?";
        final int userId = user.getId();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new UserUpdateException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    @Override
    public void updateUserPhoneNumber(final User user, final String phoneNumber) {
        final String query = "update users set phone_number = ? where id = ?";
        final int userId = user.getId();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new UserUpdateException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the email_id of the current user
     */
    @Override
    public void updateUserEmailId(final User user, final String emailId) {
        final String query = "update users set email_id = ? where id = ?";
        final int userId = user.getId();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new UserUpdateException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param password Represents the password of the current user
     */
    @Override
    public void updateUserPassword(User user, String password) {
        final String query = "update users set password = ? where id = ?";
        final int userId = user.getId();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new UserUpdateException(message.getMessage());
        }
    }
}
