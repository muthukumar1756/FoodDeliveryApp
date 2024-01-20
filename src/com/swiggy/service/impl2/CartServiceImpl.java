package com.swiggy.service.impl2;

import com.swiggy.dao.CartDAO;
import com.swiggy.dao.impl.CartDAOImpl;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;
import com.swiggy.service.CartService;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class CartServiceImpl implements CartService{

    private static CartService cartService;

    private final CartDAO cartDAO;

    private CartServiceImpl() {
        cartDAO = CartDAOImpl.getInstance();
    }

    /**
     * <p>
     * Gets the cart service implementation class object.
     * </p>
     *
     * @return The cart service implementation object
     */
    public static CartService getInstance() {
        if (null == cartService) {
            cartService = new CartServiceImpl();
        }

        return cartService;
    }

    /**
     * {@inheritDoc}
     *
     * @param food Represents the current {@link Food} selected by the user
     * @param user Represents the current {@link User}
     * @param quantity Represents the quantity of the {@link Food} given by the current user
     * @param restaurantId Represents the id of the current {@link Restaurant}
     * @return True if the food is added to the user cart, false otherwise
     */
    @Override
    public boolean addFoodToCart(final Food food, final User user, final int quantity, final int restaurantId) {
      return cartDAO.addFoodToCart(food, user, quantity, restaurantId);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return The map having all the foods from the user cart
     */
    @Override
    public Map<Food, Integer> getCart(final User user) {
        return cartDAO.getCart(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @param food Represents the current {@link Food} selected by the user
     * @return True if the food is removed,false otherwise
     */
    @Override
    public boolean removeFood(final User user, final Food food) {
        return cartDAO.removeFood(user, food);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the current {@link User}
     * @return The true if the cart is cleared, false otherwise
     */
    @Override
    public boolean clearCart(final User user) {
        return cartDAO.clearCart(user);
    }
}
