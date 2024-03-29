package com.swiggy.service;

import com.swiggy.model.User;

/**
 * <p>
 * Provides the services for the user orders.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.0
 */
public interface OrderService {

    /**
     * <p>
     * places the user orders.
     * </p>
     *
     * @param user Represents the current {@link User}
     * @return True if the order is placed, false otherwise
     */
    boolean placeOrder(final User user);
}
