package com.swiggy.customexception;

/**
 * <p>
 * Handles the exception when the user cart is not updated.
 * </p>
 */
public class CartUpdateException extends RuntimeException {
    public CartUpdateException(final String message) {
        super(message);
    }
}
