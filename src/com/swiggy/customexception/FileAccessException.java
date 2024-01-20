package com.swiggy.customexception;

/**
 * <p>
 * Handles the exception when the restaurant files cant accessed.
 * </p>
 */
public class FileAccessException extends RuntimeException {
    public FileAccessException(final String message) {
        super(message);
    }
}
