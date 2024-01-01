package com.swiggy.view;

import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;
import com.swiggy.view.validation.UserDataValidator;

import java.util.List;
import java.util.Scanner;

public class CartView {

    private static CartView instance;
    private static final RestaurantView RESTAURANT_VIEW = RestaurantView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    public static final Scanner SCANNER = ScannerInstance.getInstance();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();
    private int totalAmount;

    private CartView() {
    }

    public static CartView getInstance(){
        if (instance == null) {
            instance = new CartView();
        }

        return instance;
    }

    public void printCart(final Restaurant selectedRestaurant, final List<Food> userCart, final User currentUser) {
        System.out.println("Items In Your Cart \n");

        for(final Food food : userCart){
            totalAmount += food.getRate();

            System.out.println((userCart.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        System.out.println("Total Amount: " + totalAmount + "\n");
        totalAmount = 0;

        manageCartChoice(selectedRestaurant, userCart, currentUser);
    }

    private void manageCartChoice(final Restaurant selectedRestaurant, final List<Food> userCart, final User currentUser) {
        System.out.println("1.Place Order\n2.Remove Item From Cart\n3.Clear All Item From The Cart\n4.To Go Back And Add More Food");
        final String userChoice = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
            RESTAURANT_VIEW.addMoreFood(selectedRestaurant, currentUser);
        }
        switch (Integer.parseInt(userChoice)) {
            case 1:
                placeOrder(selectedRestaurant, userCart, currentUser);
                break;
            case 2:
                removeItem(selectedRestaurant, userCart, currentUser);
                break;
            case 3:
                clearAllCartItems(userCart, currentUser);
                break;
            case 4:
                RESTAURANT_VIEW.addMoreFood(selectedRestaurant, currentUser);
                break;
            default:
                System.out.println("Enter A Valid Option");
                manageCartChoice(selectedRestaurant, userCart, currentUser);
        }
    }

    private void placeOrder(final Restaurant selectedRestaurant, final List<Food> userCart, final User currentUser) {
        if (userCart.isEmpty()) {
            System.out.println("Your Cart Is Empty\nPlease Select A option From Below:\n1.Continue Ordering Foods\n2.Logout");
            final String userChoice = SCANNER.nextLine().trim();

            if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
                RESTAURANT_VIEW.addMoreFood(selectedRestaurant, currentUser);
            }
            switch (Integer.parseInt(userChoice)) {
                case 1:
                    RESTAURANT_VIEW.displayRestaurants(currentUser);
                    break;
                case 2:
                    System.out.println("Your Account Is Logged Out");
                    USER_VIEW.printMainMenu();
                    break;
                default:
                    System.out.println("Enter A Valid Option");
                    placeOrder(selectedRestaurant, userCart, currentUser);
            }
        } else {
            System.out.println("Enter Your Address");
            final String address = SCANNER.nextLine().trim();

            if (USER_DATA_VALIDATOR.validateBackOption(address)) {
                printCart(selectedRestaurant, userCart, currentUser);
            }
            System.out.println("\n Your Order Is Placed..\nWill Shortly Delivered To This Address : "+ address);
            continueOrdering(currentUser);
        }
    }

    private void removeItem(final Restaurant selectedRestaurant, final List<Food> userCart, final User currentUser) {
        System.out.println("Enter The Item Number To Remove");
        final String itemNumber = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(itemNumber)) {
            printCart(selectedRestaurant, userCart, currentUser);
        }

        try {
            userCart.remove(Integer.parseInt(itemNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter The Valid Item Number");
            removeItem(selectedRestaurant, userCart, currentUser);
        }
        System.out.println("The Item Is Removed");
        printCart(selectedRestaurant, userCart, currentUser);
    }

    private void continueOrdering(final User currentUser) {
        System.out.println("1.Continue Food Ordering\n2.Logout");
        final String userChoice = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
            RESTAURANT_VIEW.displayRestaurants(currentUser);
        }

        switch (Integer.parseInt(userChoice)) {
            case 1:
                RESTAURANT_VIEW.displayRestaurants(currentUser);
                break;
            case 2:
                System.out.println("Your Account Is Logged Out");
                USER_VIEW.printMainMenu();
                break;
            default:
                System.out.println("Invalid Option");
                continueOrdering(currentUser);
        }
    }

    private void clearAllCartItems(final List<Food> userCart, final User currentUser) {
        userCart.clear();
        System.out.println("Your Cart Is Empty");
        continueOrdering(currentUser);
    }
}