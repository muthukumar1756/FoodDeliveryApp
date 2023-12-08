package swiggy.service;

import swiggy.model.Food;
import swiggy.model.Restaurant;
import swiggy.model.User;
import swiggy.view.CartView;
import swiggy.view.RestaurantView;

import java.util.List;

public class FoodOrderService {

    private static FoodOrderService instance;
    private static final RestaurantView RESTAURANT_VIEW_INSTANCE = RestaurantView.getInstance();
    private static final CartView CART_VIEW_INSTANCE = CartView.getInstance();

    private FoodOrderService () {
    }

    public static FoodOrderService getInstance() {
        if (instance == null) {
            instance = new FoodOrderService();
        }

        return instance;
    }

}