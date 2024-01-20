package com.swiggy.service.impl;

import com.swiggy.model.Order;
import com.swiggy.model.User;
import com.swiggy.service.OrderService;

/**
 * <p>
 *
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {

    private static OrderService orderService;

    private OrderServiceImpl() {
    }

    /**
     * <p>
     * Gets the cart service implementation class object.
     * </p>
     *
     * @return The cart service implementation object
     */
    public static OrderService getInstance() {
        if (null == orderService) {
            orderService = new OrderServiceImpl();
        }

        return orderService;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return True if the order is placed, false otherwise
     */
    @Override
    public boolean placeOrder(final User user) {
        final String address = user.getAddress();
        final Order order = new Order();

        order.setUserId(user.getId());
        order.setAddress(address);

        return true;
    }
}
