package com.swiggy.dao;

import com.swiggy.model.User;

/**
 * <p>
 * Provides data base service for the user
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface UserDAO {

    /**
     * <p>
     * Creates the new user
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
     * @param name Represents the name of the current user
     */
    void updateUserName(final User user, final String name);

    /**
     * <p>
     * Updates the email_id of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param phoneNumber Represents the phone_number of the current user
     */
    void updateUserPhoneNumber(final User user, final String phoneNumber);

    /**
     * <p>
     * Updates the password of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param emailId Represents the email_id of the current user
     */
    void updateUserEmailId(final User user, final String emailId);

    /**
     * <p>
     * Updates the phone_number of the current user.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @param password Represents the password of the current user
     */
    void updateUserPassword(final User user, final String password);
}
