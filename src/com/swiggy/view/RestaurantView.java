package com.swiggy.view;

import java.util.List;
import java.util.Map;

import com.swiggy.controller.CartController;
import com.swiggy.controller.RestaurantController;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;

import static com.swiggy.view.UserView.scanner;

public class RestaurantView {

    private static RestaurantView instance;
    private static final CartController CART_CONTROLLER_INSTANCE = CartController.getInstance();
    private static final RestaurantController RESTAURANT_CONTROLLER_INSTANCE = RestaurantController.getInstance();
    private static final CartView CART_VIEW_INSTANCE = CartView.getInstance();
    private static final Map <Integer, Restaurant> RESTAURANTS = RESTAURANT_CONTROLLER_INSTANCE.getRestaurantsList();

    private RestaurantView() {
    }

    public static RestaurantView getInstance() {
        if (instance == null) {
            instance = new RestaurantView();
        }

        return instance;
    }

    public void displayRestaurants(final User currentUser) {
        System.out.println("Available restaurants:");
        System.out.println("Hotel id" + " " + " Hotel Name");

        for (Map.Entry<Integer, Restaurant> restaurants : RESTAURANTS.entrySet()) {
            int restaurantId = restaurants.getKey();
            Restaurant restaurant = restaurants.getValue();

            System.out.println( restaurantId + " " + restaurant.getName());
        }
            selectRestaurant(currentUser);
    }

    public void selectRestaurant(final User currentUser) {
        int restaurantNumber = scanner.nextInt();
        Restaurant selectedRestaurant = RESTAURANTS.get(restaurantNumber);

        selectFood(selectedRestaurant, currentUser);
    }

    public void selectFood(final Restaurant selectedRestaurant, final User currentUser) {
        List<Food> selectedRestaurantMenuCard = null;

        try {
            selectedRestaurantMenuCard = selectedRestaurant.getMenuCard();
        } catch (NullPointerException e) {
            System.out.println("Enter valid option");
            displayRestaurants(currentUser);
        }

        System.out.println("Available Items:");
        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }

        selectFilter(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    private void selectFilter(final Restaurant selectedRestaurant, final User currentUser, List<Food> selectedRestaurantMenuCard) {
        System.out.println("\nFor Filter enter 1 else enter 2");
        int filterOption = scanner.nextInt();

        switch (filterOption) {
            case 1:
                filterView(selectedRestaurant, currentUser);
                break;
            case 2:
                addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
                break;
            default:
                System.out.println("Invalid option try again\n");
                selectFood(selectedRestaurant, currentUser);
        }
    }

    private void filterView (final Restaurant selectedRestaurant, final User currentUser) {
        System.out.println("Filter Type");
        System.out.println("1.Veg");
        System.out.println("2.Non-Veg");
        int filterTypeOption = scanner.nextInt();

        switch (filterTypeOption) {
            case 1:
                selectVegFood(selectedRestaurant, currentUser);
                break;
            case 2:
                selectNonVegFood(selectedRestaurant, currentUser);
                break;
            default:
                System.out.println("Enter valid option");
                filterView(selectedRestaurant, currentUser);
        }
    }

    public void selectVegFood(final Restaurant selectedRestaurant, final User currentUser) {
        List<Food> selectedRestaurantMenuCard = selectedRestaurant.getVegMenuCard();

        System.out.println("Available Items:");
        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    public void selectNonVegFood(final Restaurant selectedRestaurant, final User currentUser) {
        List<Food> selectedRestaurantMenuCard = selectedRestaurant.getNonVegMenuCard();

        System.out.println("Available Items:");
        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        addFoodToCart(selectedRestaurant, currentUser, selectedRestaurantMenuCard);
    }

    public void addFoodToCart (final Restaurant selectedRestaurant, final User currentUser, final List<Food> selectedRestaurantMenuCard) {
        try {
            System.out.println("Enter the food id to order");
            int foodNumber = scanner.nextInt();
            Food selectedFood = selectedRestaurantMenuCard.get(foodNumber - 1);

            CART_CONTROLLER_INSTANCE.addSelectedFoodToCart(selectedFood, currentUser);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter a valid option from the Menucard");
            selectFood(selectedRestaurant, currentUser);
        }
        addMoreFood(selectedRestaurant, currentUser);
    }

    public void addMoreFood(final Restaurant selectedRestaurant, final User currentUser) {
        System.out.println("Do you want to add more food");
        System.out.println("1.Yes go to Menucard");
        System.out.println("2.No go to cart");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                selectFood(selectedRestaurant, currentUser);
                break;
            case 2:
                List<Food> userCart = currentUser.getCart();

                CART_VIEW_INSTANCE.printCart(userCart, currentUser);
                break;
            default:
                System.out.println("Enter a valid option");
                addMoreFood(selectedRestaurant, currentUser);
        }
    }

}