package com.swiggy.view;

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

    private final RestaurantView restaurantView;
    private final CartController cartController;

    private float totalAmount;

    private CartView() {
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
    public static CartView getInstance(){
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
    public void printCart(final Restaurant restaurant, final User user) {
        final Map<Food, Integer> cart = cartController.getCart(user);
        printMessage("Items In Your Order \n");

        for(final Food food : cart.keySet()){
            totalAmount += food.getRate();
            final int quantity = cart.get(food);
            int index = 1;

            System.out.println(String.format("%d %s %.2f %s",
                    index,
                    food.getFoodName(),
                    food.getRate() * quantity,
                    food.getType()));
            ++index;
        }
        System.out.println("Total Amount: " + totalAmount + "\n");
        totalAmount = 0;

        handleCartChoice(restaurant, cart, user);
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
    private void handleCartChoice(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        printMessage("1.Place Order\n2.Remove Item From Order\n3.Clear All Item From The Order\n4.To Go Back And Add More Food");
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
                clearCart(cart, user);
                break;
            case 4:
                restaurantView.addFoodOrPlaceOrder(restaurant, user);
                break;
            default:
                printMessage("Enter A Valid Option");
                handleCartChoice(restaurant, cart, user);
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
        printMessage("Enter The Item Number To Remove");
        final int itemNumber = getChoice();

        if (-1 == itemNumber) {
            printCart(restaurant, user);
        }
        final int selectedIndex = itemNumber - 1;

        if (selectedIndex >= 0 && selectedIndex < cart.size()) {
            final List<Food> foodCart = new ArrayList<>(cart.keySet());
            final Food removeFood = foodCart.get(selectedIndex);

            cart.remove(removeFood);

            if (cartController.removeFood(user, removeFood)) {
                printMessage("The Item Is Removed");
            }
        } else {
            printMessage("Enter The Valid Item Number");
        }
        printCart(restaurant, user);
    }

    /**
     * <p>
     * Handles the users choice to display restaurant or logout.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    public void displayRestaurantOrLogout(final User user) {
        printMessage("1.Continue Food Ordering\n2.Logout");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            restaurantView.displayRestaurants(user);
        }

        switch (userChoice) {
            case 1:
                restaurantView.displayRestaurants(user);
                break;
            case 2:
                printMessage("Your Account Is Logged Out");
                UserView.getInstance().displayMainMenu();
                break;
            default:
                printMessage("Invalid Option");
                displayRestaurantOrLogout(user);
        }
    }

    /**
     * <p>
     * Removes all the food from the user cart.
     * </p>
     *
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     */
    private void clearCart(final Map<Food, Integer> cart, final User user) {
        cart.clear();

        if (cartController.clearCart(user)) {
            printMessage("Your Order Is Empty");
        }
        displayRestaurantOrLogout(user);
    }
}