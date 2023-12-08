package swiggy.view;

import swiggy.controller.CartController;
import swiggy.model.Cart;
import swiggy.model.Food;
import swiggy.model.User;

import java.util.List;

import static swiggy.view.UserView.scanner;

public class CartView {

    private static CartView instance;
    private static final CartController CART_CONTROLLER_INSTANCE = CartController.getInstance();
    private static final RestaurantView RESTAURANT_VIEW_INSTANCE = RestaurantView.getInstance();
    private static final UserView USER_VIEW_INSTANCE = UserView.getInstance();
    private int total;

    private CartView(){
    }

    public static CartView getInstance(){
        if (instance == null) {
            instance = new CartView();
        }

        return instance;
    }

    public void printCart(List<Food> userCart, User currentUser) {
            System.out.println("Items in your cart \n");
        for(Food food:userCart){
            total += food.getRate();

            System.out.println((userCart.indexOf(food) + 1) + " " + food.getName() + " " + food.getRate() + " " + food.getType());
        }
        System.out.println("Total amount: " + total + "\n");
        total = 0;

        processSelectedCartOption(userCart, currentUser);
    }

    private void processSelectedCartOption(List<Food> userCart, User currentUser) {
        System.out.println("1.Place order");
        System.out.println("2.Remove item from cart");
        System.out.println("3.clear all item from the cart");
        int selectedCartOption = scanner.nextInt();

        switch (selectedCartOption) {
            case 1:
                placeOrder(userCart, currentUser);
                break;
            case 2:
                removeItemFromCart(userCart, currentUser);
                break;
            case 3:
                clearAllCartItems(userCart, currentUser);
                break;
            default:
                System.out.println("Enter a valid option");
                processSelectedCartOption(userCart, currentUser);
        }
    }

    private void placeOrder(List<Food> userCart, User currentUser) {
        if (userCart.isEmpty()) {
            System.out.println("Your cart is empty");
            System.out.println("Please select a option from below:");
            System.out.println("1.Continue ordering foods");
            System.out.println("2.Logout");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    RESTAURANT_VIEW_INSTANCE.displayRestaurants(currentUser);
                    break;
                case 2:
                    System.out.println("Your account is logged out");
                    USER_VIEW_INSTANCE.printMenu();
                    break;
                default:
                    System.out.println("Enter a valid option");
                    placeOrder(userCart, currentUser);
            }
        } else {
            System.out.println("Enter your address");
            String address = scanner.next();

            System.out.println("\n Your order is Placed..");
            System.out.println("will shortly delivered to this Address : " + address);
            continueOrdering(currentUser);
        }
    }

    private void removeItemFromCart(List<Food> userCart, User currentUser) {
        System.out.println("Enter the item number to remove");
        int itemNumber = scanner.nextInt();

        userCart.remove(itemNumber - 1);
        System.out.println("The Item is removed");
        printCart(userCart, currentUser);
    }

    private void continueOrdering(User currentUser) {
        System.out.println("1.Continue Food Ordering");
        System.out.println("2.Logout");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                RESTAURANT_VIEW_INSTANCE.displayRestaurants(currentUser);
                break;
            case 2:
                System.out.println("Your account is logged out");
                USER_VIEW_INSTANCE.printMenu();
                break;
            default:
                System.out.println("Invalid option");
                continueOrdering(currentUser);
        }
    }

    private void clearAllCartItems(List<Food> userCart, User currentUser) {
        userCart.clear();
        System.out.println("Your cart is empty");
        continueOrdering(currentUser);
    }
}
