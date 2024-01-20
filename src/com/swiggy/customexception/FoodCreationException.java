package com.swiggy.customexception;

/**
 * <p>
 * Handles the exception when the food cant be created.
 * </p>
 */
public class FoodCreationException extends RuntimeException {
    public FoodCreationException(final String message) {
        super(message);
    }
}
