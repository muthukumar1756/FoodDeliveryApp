package com.swiggy.service.impl;

import com.swiggy.model.User;
import com.swiggy.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implements the service of the user related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static UserService userService;

    private final Map<String, User> users = new HashMap<>();

    private UserServiceImpl() {
    }

    /**
     * <p>
     * Gets the object of the user service implementation class.
     * </p>
     *
     * @return The user service implementation object
     */
    public static UserService getInstance() {
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
        if (! users.containsKey(user.getPhoneNumber())) {
                if(! users.containsKey((user.getEmailId()))) {
                    users.put(user.getPhoneNumber(), user);
                    return true;
                } else {
                    return false;
                }
        } else {
            return false;
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
        return users.containsKey(phoneNumber);
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
            final User currentUser = users.get(phoneNumber);
            final String currentUserPassword = currentUser.getPassword();

            if (password.equals(currentUserPassword)) {
                return currentUser;
            } else {
                return null;
            }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param name Represents the name of the current user
     */
    @Override
    public void updateUserName(final User user, final String name) {
        user.setName(name);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param password Represents the password of the current user
     */
    @Override
    public void updateUserPassword(final User user, final String password) {
        user.setPassword(password);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    @Override
    public void updateUserPhoneNumber(final User user, final String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the email_id of the current user
     */
    @Override
    public void updateUserEmailId(final User user, final String emailId) {
        user.setEmailId(emailId);
    }
}
