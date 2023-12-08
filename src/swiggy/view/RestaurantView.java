package swiggy.view;

import java.util.List;
import java.util.Map;

import swiggy.controller.FoodOrderController;
import swiggy.model.Food;
import swiggy.model.Restaurant;
import swiggy.model.User;

import static swiggy.Main.restaurants;
import static swiggy.view.UserView.scanner;

public class RestaurantView {

    private static RestaurantView instance;
    private static final FoodOrderController FOOD_ORDER_CONTROLLER_INSTANCE = FoodOrderController.getInstance();
    private static final CartView CART_VIEW_INSTANCE = CartView.getInstance();

    private RestaurantView() {
    }

    public static RestaurantView getInstance() {
        if (instance == null) {
            instance = new RestaurantView();
        }

        return instance;
    }

    public void displayRestaurants(User currentUser) {
        System.out.println("Available restaurants:");
        System.out.println("Hotel id" + " " + " Hotel Name");

        for (Map.Entry<Integer, Restaurant> menu : restaurants.entrySet()) {
            int id = menu.getKey();
            Restaurant restaurant = menu.getValue();

            System.out.println( id + " " + restaurant.getName());
        }
            selectRestaurant(currentUser);
    }

    public void selectRestaurant(User currentUser) {
        int restaurantNumber = scanner.nextInt();
        Restaurant selectedRestaurant = restaurants.get(restaurantNumber);

        selectFood(selectedRestaurant, currentUser);
    }

    public void selectFood(Restaurant selectedRestaurant, User currentUser) {
        List<Food> selectedRestaurantMenuCard = null;

        try {
            selectedRestaurantMenuCard = selectedRestaurant.getMenuCardList();
        } catch (Exception e) {
            System.out.println("Enter valid option");
            displayRestaurants(currentUser);
        }

        System.out.println("Available Items:");
        for (Food food : selectedRestaurantMenuCard) {
            System.out.println((selectedRestaurantMenuCard.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        try {
            int foodNumber = scanner.nextInt();
            Food selectedFood = selectedRestaurantMenuCard.get(foodNumber - 1);

            FOOD_ORDER_CONTROLLER_INSTANCE.addSelectedFoodToCart(selectedFood, currentUser);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter a valid option from the Menucard");
            selectFood(selectedRestaurant, currentUser);
        }
        addMoreFood(selectedRestaurant, currentUser);
    }

    public void addMoreFood(Restaurant selectedRestaurant, User currentUser) {
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

