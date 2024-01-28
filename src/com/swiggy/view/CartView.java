package com.swiggy.view;

import org.apache.log4j.Logger;

import com.swiggy.controller.CartController;
import com.swiggy.model.Cart;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Displays and updates the cart of the user
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class CartView extends CommonView {

    private static CartView cartView;

    private final Logger logger;
    private final RestaurantView restaurantView;
    private final CartController cartController;

    private float totalAmount;

    private CartView() {
        logger = Logger.getLogger(CartView.class);
        restaurantView = RestaurantView.getInstance();
        cartController = CartController.getInstance();
    }

    /**
     * <p>
     * Gets the object of the cart view class.
     * </p>
     *
     * @return The cart view object
     */
    public static CartView getInstance() {
        if (null == cartView) {
            cartView = new CartView();
        }

        return cartView;
    }

    /**
     * <p>
     * Displays all the foods in the user cart.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     */
    public void displayCart(final Restaurant restaurant, final User user) {
        final Map<Food, Integer> cart = cartController.getCart(user);
        int index = 1;

        logger.info("Items In Your Cart \n");
        logger.info("ID | Name | Quantity | Rate | Category ");

        for(final Food food : cart.keySet()){
            final int quantity = cart.get(food);
            totalAmount += food.getRate() * quantity;

            logger.info(String.format("%d %s %d %.2f %s", index, food.getFoodName(), quantity,
                    food.getRate() * quantity, food.getType()));
            ++index;
        }
        logger.info(String.format("Total Amount: RS %.2f \n", totalAmount));
        totalAmount = 0;

        displayCartMenu(restaurant, cart, user);
    }

    /**
     * <p>
     * Handles the users choice to place order or remove food from the user cart.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     */
    private void displayCartMenu(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        logger.info("1.Place Order\n2.Remove Item From Cart\n3.Clear All Item From The Cart\n4.To Go Back And Add More Food");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            restaurantView.addFoodOrPlaceOrder(restaurant, user);
        }

        switch (userChoice) {
            case 1:
                OrderView.getInstance().placeOrder(restaurant, cart, user);
                break;
            case 2:
                removeFood(restaurant, cart, user);
                break;
            case 3:
                clearCart(user);
                break;
            case 4:
                restaurantView.addFoodOrPlaceOrder(restaurant, user);
                break;
            default:
                logger.warn("Enter A Valid Option");
                displayCartMenu(restaurant, cart, user);
        }
    }

    /**
     * <p>
     * Gets the users choice to remove the food from the user cart.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     */
    private void removeFood(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        logger.info("Enter The Item Number To Remove");
        final int itemNumber = getChoice();

        if (-1 == itemNumber) {
            displayCart(restaurant, user);
        }
        final int selectedIndex = itemNumber - 1;

        if (selectedIndex >= 0 && selectedIndex < cart.size()) {
            final List<Food> foodCart = new ArrayList<>(cart.keySet());
            final Food removeFood = foodCart.get(selectedIndex);

            if (cartController.removeFood(user, removeFood)) {
                logger.info("The Item Is Removed");
            }
        } else {
            logger.warn("Enter The Valid Item Number");
        }
        displayCart(restaurant, user);
    }

    /**
     * <p>
     * Handles the users choice to display restaurant or logout.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    public void displayRestaurantOrLogout(final User user) {
        logger.info("1.Continue Food Ordering\n2.Logout");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            restaurantView.displayRestaurants(user);
        }

        switch (userChoice) {
            case 1:
                restaurantView.displayRestaurants(user);
                break;
            case 2:
                logger.info("Your Account Is Logged Out");
                UserView.getInstance().displayMainMenu();
                break;
            default:
                logger.warn("Invalid Option");
                displayRestaurantOrLogout(user);
        }
    }

    /**
     * <p>
     * Removes all the food from the user cart.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    public void clearCart(final User user) {
        if (cartController.clearCart(user)) {
            logger.info("Your Cart Is Empty");
        }
        displayRestaurantOrLogout(user);
    }
}