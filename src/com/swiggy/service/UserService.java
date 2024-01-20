package com.swiggy.service;

import com.swiggy.model.User;

/**
 * <p>
 * Provides the services for the user.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Creates the new user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @return True if user is created, false otherwise
     */
    boolean createUser(final User user);

    /**
     * <p>
     * Gets the user if the phone_number and password matches.
     * </p>
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @param password Represents the password of the current user
     * @return The current user
     */
    User getUser(final String phoneNumber, final String password);

    /**
     * <p>
     * Checks for the user is already exist or not.
     * </p>
     *
     * @param phoneNumber Represents the phone_number of the current user
     * @return True if the user is exist, false otherwise
     */
    boolean isUserExist(final String phoneNumber);

    /**
     * <p>
     * Updates the user_name of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param userName Represents the name of the current user
     */
    void updateUserName(final User user, final String userName);

    /**
     * <p>
     * Updates the user_name of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the name of the current user
     */
    void updateUserPhoneNumber(final User user, final String phoneNumber);

    /**
     * <p>
     * Updates the user_name of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the name of the current user
     */
    void updateUserEmailId(final User user, final String emailId);

    /**
     * <p>
     * Updates the user_name of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param password Represents the name of the current user
     */
    void updateUserPassword(final User user, final String password);
}
