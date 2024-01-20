package com.swiggy.dao;

import com.swiggy.model.User;

/**
 * <p>
 * Provides data base service for the user order
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface OrderDAO {

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
