package com.swiggy.exception;

/**
 * <p>
 * Handles the exception when the restaurant cant be created.
 * </p>
 */
public class RestaurantCreationException extends RuntimeException {
    public RestaurantCreationException(final String message) {
        super(message);
    }
}