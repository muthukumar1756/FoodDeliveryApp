package com.swiggy.service.impl2;

import com.swiggy.dao.UserDAO;
import com.swiggy.dao.impl.UserDAOImpl;
import com.swiggy.model.User;
import com.swiggy.service.UserService;

/**
 * <p>
 * Implements the service of the user related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private final UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDAOImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the user service implementation class.
     * </p>
     *
     * @return The user service implementation object
     */
    public static UserServiceImpl getInstance() {
        if (null == userService) {
            userService = new UserServiceImpl();
        }

        return userService;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return True if user is created, false otherwise
     */
    @Override
    public boolean createUser(final User user) {
       return userDAO.createUser(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @param password Represents the password of the current user
     * @return The current user
     */
    @Override
    public User getUser(final String phoneNumber, final String password) {
        return userDAO.getUser(phoneNumber, password);
    }

    /**
     * {@inheritDoc}
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @return True if the user is exist, false otherwise
     */
    @Override
    public boolean isUserExist(final String phoneNumber) {
        return userDAO.isUserExist(phoneNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param name Represents the name of the current user
     */
    @Override
    public void updateUserName(final User user, final String name) {
        userDAO.updateUserName(user, name);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    @Override
    public void updateUserPhoneNumber(final User user, final String phoneNumber) {
        userDAO.updateUserPhoneNumber(user, phoneNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the email_id of the current user
     */
    @Override
    public void updateUserEmailId(final User user, final String emailId) {
        userDAO.updateUserEmailId(user, emailId);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    @Override
    public void updateUserPassword(final User user, final String phoneNumber) {
        userDAO.updateUserPassword(user, phoneNumber);
    }
}
