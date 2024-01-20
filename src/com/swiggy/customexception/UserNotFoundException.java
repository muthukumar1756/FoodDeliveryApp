package com.swiggy.customexception;

/**
 * <p>
 * Handles the exception when the user is not found.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message) {
        super(message);
    }
}