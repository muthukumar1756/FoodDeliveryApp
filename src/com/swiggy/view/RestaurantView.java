package com.swiggy.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.swiggy.controller.CartController;
import com.swiggy.controller.RestaurantController;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;
import com.swiggy.view.validation.UserDataValidator;

public class RestaurantView {

    private static RestaurantView instance;
    private static final Scanner SCANNER = ScannerInstance.getInstance();
    private static final CartController CART_CONTROLLER = CartController.getInstance();
    private static final RestaurantController RESTAURANT_CONTROLLER = RestaurantController.getInstance();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final Map<Integer, Restaurant> RESTAURANTS = RESTAURANT_CONTROLLER.getRestaurantsList();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();

    private RestaurantView() {
    }

    public static RestaurantView getInstance() {
        if (instance == null) {
            instance = new RestaurantView();
        }

        return instance;
    }

    public void displayRestaurants(final User currentUser) {
        System.out.println("To Go Back Enter *\nAvailable Restaurants:");

        for (final Map.Entry<Integer, Restaurant> restaurants : RESTAURANTS.entrySet()) {
            final int restaurantId = restaurants.getKey();
            final Restaurant restaurant = restaurants.getValue();
            String restaurantData = String.format("%d %s",restaurantId,restaurant.getName());

            System.out.println(restaurantData);
        }
        selectRestaurant(currentUser);
    }

    private void selectRestaurant(final User currentUser) {
        final String restaurantNumber = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(restaurantNumber)) {
            USER_VIEW.displayUserOptions(currentUser);
        }
        final Restaurant selectedRestaurant = RESTAURANTS.get(Integer.parseInt(restaurantNumber));

        printMenuCard(selectedRestaurant, currentUser);
    }

    private void printMenuCard(final Restaurant selectedRestaurant, final User currentUser) {
        List<Food> selectedRestaurantMenuCard = null;

        try {
            selectedRestaurantMenuCard = selectedRestaurant.getMenuCard();
        } catch (NullPointerException e) {
            System.out.println("Enter Valid Option");
            displayRestaurants(currentUser);
        }
        System.out.println("\nAvailable Items:\n");

        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        selectFilter(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    private void selectFilter(final Restaurant selectedRestaurant, final User currentUser, final List<Food> selectedRestaurantMenuCard) {
        System.out.println("\n1.To Apply Filter\n2.To Continue Food Ordering\n3.To Select Other Restaurant");
        final String userChoice = SCANNER.nextLine();

        if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
            USER_VIEW.displayUserOptions(currentUser);
        }
        switch (Integer.parseInt(userChoice)) {
            case 1:
                selectFilterType(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
                break;
            case 2:
                addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
                break;
            case 3:
                displayRestaurants(currentUser);
                break;
            default:
                System.out.println("Invalid Option Try Again\n");
                printMenuCard(selectedRestaurant, currentUser);
        }
    }

    private void addFoodToCart(final Restaurant selectedRestaurant, final User currentUser, final List<Food> selectedRestaurantMenuCard) {
        try {
            System.out.println("Enter FoodId to order");
            final String foodNumber = SCANNER.nextLine().trim();

            if (USER_DATA_VALIDATOR.validateBackOption(foodNumber)) {
                displayRestaurants(currentUser);
            }
            final Food selectedFood = selectedRestaurantMenuCard.get(Integer.parseInt(foodNumber) - 1);
            System.out.println("Enter The Quantity");
            final int quantity = Integer.parseInt(SCANNER.nextLine());

            CART_CONTROLLER.addSelectedFoodToCart(selectedFood, currentUser);
            CART_CONTROLLER.addSelectedFoodToCart(selectedFood, currentUser, quantity);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter A Valid Option From The Menucard");
            printMenuCard(selectedRestaurant, currentUser);
        }
        addMoreFood(selectedRestaurant, currentUser);
    }

    private void selectFilterType(final Restaurant selectedRestaurant, final User currentUser, final List<Food> selectedRestaurantMenuCard) {
        System.out.println("\nFilter Type\n1.Veg\n2.Non-Veg");
        final String filterTypeOption = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(filterTypeOption)) {
            selectFilter(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
        }

        switch (Integer.parseInt(filterTypeOption)) {
            case 1:
                selectVegFood(selectedRestaurant, currentUser);
                break;
            case 2:
                selectNonVegFood(selectedRestaurant, currentUser);
                break;
            default:
                System.out.println("Enter valid option");
                selectFilterType(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
        }
    }

    private void selectVegFood(final Restaurant selectedRestaurant, final User currentUser) {
        final List<Food> selectedRestaurantMenuCard = selectedRestaurant.getVegMenuCard();

        System.out.println("Available Items:");
        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    private void selectNonVegFood(final Restaurant selectedRestaurant, final User currentUser) {
        final List<Food> selectedRestaurantMenuCard = selectedRestaurant.getNonVegMenuCard();

        System.out.println("\nAvailable Items:");

        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    public void addMoreFood(final Restaurant selectedRestaurant, final User currentUser) {
        System.out.println("Do You Want To Add More Food\n1.Yes Go To MenuCard\n2.No Go To Cart");
        final String userChoice = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
            printMenuCard(selectedRestaurant, currentUser);
        }
        switch (Integer.parseInt(userChoice)) {
            case 1:
                printMenuCard(selectedRestaurant, currentUser);
                break;
            case 2:
                final List<Food> userCart = currentUser.getCart();

                CART_VIEW.printCart(selectedRestaurant, userCart, currentUser);
                break;
            default:
                System.out.println("Enter A Valid Option");
                addMoreFood(selectedRestaurant, currentUser);
        }
    }
}