package com.swiggy.view;

import java.util.List;
import java.util.Map;

import com.swiggy.controller.CartController;
import com.swiggy.controller.RestaurantController;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;
import com.swiggy.model.User;

/**
 * <p>
 * Displays restaurants details and menucard of the selected restaurant.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class RestaurantView extends CommonView {

    private static RestaurantView restaurantView;

    private final RestaurantController restaurantController;

    private RestaurantView() {
        restaurantController = RestaurantController.getInstance();
    }

    /**
     * <p>
     * Gets the object of the restaurant view class.
     * </p>
     *
     * @return The restaurant view object
     */
    public static RestaurantView getInstance() {
        if (null == restaurantView) {
            restaurantView = new RestaurantView();
        }

        return restaurantView;
    }

    /**
     * <p>
     * Displays the available restaurants.
     * </p>
     * @param user Represents the current {@link User}
     */
    public void displayRestaurants(final User user) {
        printMessage("To Go Back Enter *\nAvailable Restaurants In Your Area:");

        for (final Map.Entry<Integer, Restaurant> restaurants : restaurantController.getRestaurants().entrySet()) {
            System.out.println(String.format("%d %s", restaurants.getKey(), restaurants.getValue().getName()));
        }
        selectRestaurant(user);
    }

    /**
     * <p>
     * Gets the selection of a restaurant by the user.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    private void selectRestaurant(final User user) {
        final int restaurantNumber = getChoice();

        if (-1 == restaurantNumber) {
            UserView.getInstance().displayHomePageMenu(user);
        }
        final Restaurant selectedRestaurant = restaurantController.getRestaurants().get(restaurantNumber);

        if (null == selectedRestaurant) {
            printMessage("Select A Valid Restaurant Id");
            displayRestaurants(user);
        }
        displayMenuCard(selectedRestaurant, user);
    }

    /**
     * <p>
     * Displays the menucard of the restaurant selected by the user.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     */
    private void displayMenuCard(final Restaurant restaurant, final User user) {
        List<Food> selectedRestaurantMenuCard = restaurantController.getMenuCard(restaurant);

        if (null != selectedRestaurantMenuCard) {
            printMessage("\nAvailable Items:\n");

            for (final Food food : selectedRestaurantMenuCard) {
                System.out.println(String.format("%d %s %.2f %s",
                        selectedRestaurantMenuCard.indexOf(food) + 1,
                        food.getFoodName(),
                        food.getRate(),
                        food.getType()));
            }
            selectFilter(restaurant, user, selectedRestaurantMenuCard);
        } else {
            printMessage("Enter Valid Option");
            displayRestaurants(user);
        }
    }

    /**
     * <p>
     * Displays and handles the users choice to filter foods or continue with food ordering.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     * @param menuCard Represents the menucard from the selected restaurant
     */
    private void selectFilter(final Restaurant restaurant, final User user, final List<Food> menuCard) {
        printMessage("\n1.To Apply Filter\n2.To Continue Food Ordering\n3.To Select Other Restaurant");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            UserView.getInstance().displayHomePageMenu(user);
        }

        switch (userChoice) {
            case 1:
                selectFoodFilter(restaurant, user, menuCard);
                break;
            case 2:
                selectFood(restaurant, user, menuCard);
                break;
            case 3:
                displayRestaurants(user);
                break;
            default:
                printMessage("Invalid Option Try Again\n");
                displayMenuCard(restaurant, user);
        }
    }

    /**
     * <p>
     * Gets the selection of food by the user.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     * @param menuCard Represents the menucard from the selected restaurant
     */
    private void selectFood(final Restaurant restaurant, final User user, final List<Food> menuCard) {
            printMessage("Enter FoodId to order");
            final int selectedIndex = getChoice();

            if (-1 == selectedIndex) {
                displayRestaurants(user);
            }
           int foodNumber = selectedIndex - 1;

            if (foodNumber > 0 && foodNumber <= menuCard.size()) {
                final Food selectedFood = menuCard.get(foodNumber);

                getQuantity(selectedFood, user, restaurant);
            } else {
                printMessage("Enter A Valid Option From The Menucard");
                displayMenuCard(restaurant, user);
            }

        addFoodOrPlaceOrder(restaurant, user);
    }

    /**
     * <p>
     * Gets the quantity of selected food by the user.
     * </p>
     *
     * @param food Represents the {@link Food} chosen by the user
     * @param user Represents the current {@link User}
     * @param restaurant Represents the {@link Restaurant} selected by the user
     */
    private void getQuantity(final Food food, final User user, final Restaurant restaurant) {
        printMessage("Enter The Quantity");
        final int quantity = getChoice();

        if (-1 == quantity) {
            displayRestaurants(user);
        }

        if (!CartController.getInstance().addFoodToCart(food, user, quantity, restaurant.getRestaurantId())) {
            printMessage("The Entered Quantity Is Not Available");
            getQuantity(food, user, restaurant);
        }
    }

    /**
     * <p>
     * Displays and handles the users choice to get veg or nonveg food.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     * @param menuCard Represents the menucard from the selected restaurant
     */
    private void selectFoodFilter(final Restaurant restaurant, final User user, final List<Food> menuCard) {
        printMessage("\nFilter Type\n1.Veg\n2.Non-Veg");
        final int filterTypeOption = getChoice();

        if (-1 == filterTypeOption) {
            selectFilter(restaurant, user, menuCard);
        }

        switch (filterTypeOption) {
            case 1:
                selectVegFood(restaurant, user);
                break;
            case 2:
                selectNonVegFood(restaurant, user);
                break;
            default:
                printMessage("Enter valid option");
                selectFoodFilter(restaurant, user, menuCard);
        }
    }

    /**
     * <p>
     * Displays the veg foods from the selected restaurant.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     */
    private void selectVegFood(final Restaurant restaurant, final User user) {
        final List<Food> selectedRestaurantMenuCard = restaurant.getVegMenuCard();

        printMessage("Available Items:");
        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println(String.format("%d %s %.2f %s",
                    selectedRestaurantMenuCard.indexOf(food) + 1,
                    food.getFoodName(),
                    food.getRate(),
                    food.getType()));
        }
        selectFood(restaurant, user, selectedRestaurantMenuCard);
    }

    /**
     * <p>
     * Displays the nonveg foods from the selected restaurant.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     */
    private void selectNonVegFood(final Restaurant restaurant, final User user) {
        final List<Food> selectedRestaurantMenuCard = restaurant.getNonVegMenuCard();

        printMessage("\nAvailable Items:");

        for (final Food food : selectedRestaurantMenuCard) {
            System.out.println(String.format("%d %s %.2f %s",
                    selectedRestaurantMenuCard.indexOf(food) + 1,
                    food.getFoodName(),
                    food.getRate(),
                    food.getType()));
        }
        selectFood(restaurant, user, selectedRestaurantMenuCard);
    }

    /**
     * <p>
     * Displays and handles the user choice to add extra foods or to place order.
     * </p>
     *
     * @param restaurant Represents the {@link Restaurant} selected by the user
     * @param user Represents the current {@link User}
     */
    public void addFoodOrPlaceOrder(final Restaurant restaurant, final User user) {
        printMessage("Do You Want To Add More Food\n1.Add More Food \n2.Place Order");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            displayMenuCard(restaurant, user);
        }

        switch (userChoice) {
            case 1:
                displayMenuCard(restaurant, user);
                break;
            case 2:
                CartView.getInstance().printCart(restaurant, user);
                break;
            default:
                printMessage("Enter A Valid Option");
                addFoodOrPlaceOrder(restaurant, user);
        }
    }
}