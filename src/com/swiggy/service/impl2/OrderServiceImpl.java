package com.swiggy.service.impl2;

import com.swiggy.datahandler.OrderDataHandler;
import com.swiggy.datahandler.impl.OrderDataHandlerImpl;
import com.swiggy.model.Food;
import com.swiggy.model.User;
import com.swiggy.service.OrderService;

import java.util.Map;

/**
 * <p>
 * Implements the service of the user order related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {

    private static OrderService orderService;

    private final OrderDataHandler orderDataHandler;

    private OrderServiceImpl() {
        orderDataHandler = OrderDataHandlerImpl.getInstance();
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
    public boolean placeOrder(final User user, final Map<Food, Integer> cart) {
        return orderDataHandler.placeOrder(user, cart);
    }
}
