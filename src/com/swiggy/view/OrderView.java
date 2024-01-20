package com.swiggy.view;

import com.swiggy.controller.OrderController;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;
import com.swiggy.model.Cart;

import java.util.Map;

/**
 * <p>
 * Handles the food orders by the users
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class OrderView extends CommonView {

    private static OrderView orderView;

    private final CartView cartView;
    private final RestaurantView restaurantView;
    private final OrderController orderController;

    private OrderView() {
        restaurantView = RestaurantView.getInstance();
        cartView = CartView.getInstance();
        orderController = OrderController.getInstance();
    }

    /**
     * <p>
     * Gets the object of the order view class.
     * </p>
     *
     * @return The order view object
     */
    public static OrderView getInstance(){
        if (null == orderView) {
            orderView = new OrderView();
        }

        return orderView;
    }

    /**
     * <p>
     * Handles the order placing by the user
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     */
    public void placeOrder(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        if (!cart.isEmpty()) {
            final String userAddress = getDeliveryAddress(restaurant, cart, user);

            if (orderController.placeOrder(user)) {
                System.out.println("\n Your Order Is Placed..\nWill Shortly Delivered To This Address : "+ userAddress);
                cartView.displayRestaurantOrLogout(user);
            }
        } else {
            handleEmptyCart(restaurant, cart, user);
        }
    }

    /**
     * <p>
     * Gets the delivery address of the current user.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     * @return The address of the current user
     */
    private String getDeliveryAddress(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        printMessage("Enter Your Address");
        printMessage("Enter Your Door Number");
        final String doorNum = getInfo();

        printMessage("Enter Your Street Name");
        final String streetName = getInfo();

        printMessage("Enter Your Area Name");
        final String areaName = getInfo();

        printMessage("Enter Your City Name");
        final String cityName = getInfo();

        final String userAddress = String.join(" ", doorNum, streetName, areaName, cityName);
        user.setAddress(userAddress);

        if ("back".equals(doorNum) || "back".equals(streetName) || "back".equals(areaName) ||
                "back".equals(cityName)) {
            cartView.printCart(restaurant, user);
        }

        return userAddress;
    }

    /**
     * <p>
     * Handles the user cart if it has no foods.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param cart Represents the {@link Cart} of the current user
     * @param user Represents the current {@link User}
     */
    private void handleEmptyCart(final Restaurant restaurant, final Map<Food, Integer> cart, final User user) {
        printMessage("Your Order Is Empty\nPlease Select A option From Below:\n1.To Order Foods\n2.Logout");
        final int userChoice = getChoice();

        if (- 1 == userChoice) {
            restaurantView.addFoodOrPlaceOrder(restaurant, user);
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
                printMessage("Enter A Valid Option");
                placeOrder(restaurant, cart, user);
        }
    }
}
