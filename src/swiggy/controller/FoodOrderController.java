package swiggy.controller;

import swiggy.model.Restaurant;
import swiggy.model.User;
import swiggy.service.FoodOrderService;
import swiggy.service.UserService;
import swiggy.model.Food;

public class FoodOrderController {

    private static FoodOrderController instance;
    private static final UserService USER_SERVICE_INSTANCE = UserService.getInstance();
    private static final FoodOrderService FOOD_ORDER_SERVICE_INSTANCE = FoodOrderService.getInstance();

    private FoodOrderController () {
    }

    public static FoodOrderController getInstance() {
        if (instance == null) {
            instance = new FoodOrderController();
        }

        return instance;
    }

    public void addSelectedFoodToCart(Food selectedFood, User currentUser) {
        currentUser.addFoodToCart(selectedFood);
    }
}
