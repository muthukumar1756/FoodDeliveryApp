package com.swiggy.controller;

import com.swiggy.model.User;
import com.swiggy.service.UserService;
import com.swiggy.service.impl2.UserServiceImpl;

/**
 * <p>
 * Handles the user related operation and responsible for processing user input.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserController {

    private static UserController userController;

    private final UserService userService;

    private UserController() {
        userService = UserServiceImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the user controller class.
     * </p>
     *
     * @return The user controller object
     */
    public static UserController getInstance() {
        if (null == userController) {
            userController = new UserController();
        }

        return userController;
    }

    /**
     * <p>
     * Creates the new user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @return True if user is created, false otherwise
     */
    public boolean createUser(final User user) {
        return userService.createUser(user);
    }

    /**
     * <p>
     * Gets the user if the phone_number and password matches.
     * </p>
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @param password Represents the password of the current user
     * @return The current user
     */
    public User getUser(final String phoneNumber, final String password) {
        return userService.getUser(phoneNumber, password);
    }

    /**
     * <p>
     * Checks for the user is already exist or not.
     * </p>
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @return True if the user is exist, false otherwise
     */
    public boolean isUserExist(final String phoneNumber) {
        return userService.isUserExist(phoneNumber);
    }

    /**
     * <p>
     * Updates the user_name of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param name Represents the name of the current user
     */
    public void updateUserName(final User user, final String name) {
        userService.updateUserName(user, name);
    }

    /**
     * <p>
     * Updates the email_id of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    public void updatePhoneNumber(final User user, final String phoneNumber) {
        userService.updateUserPhoneNumber(user, phoneNumber);
    }

    /**
     * <p>
     * Updates the password of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the email_id of the current user
     */
    public void updateEmailId(final User user, final String emailId) {
        userService.updateUserEmailId(user, emailId);
    }

    /**
     * <p>
     * Updates the phone_number of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param password Represents the password of the current user
     */
    public void updatePassword(final User user, final String password) {
        userService.updateUserPassword(user, password);
    }

    public void userExit() {

    }
}
