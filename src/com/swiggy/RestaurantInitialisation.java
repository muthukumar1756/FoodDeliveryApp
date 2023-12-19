package com.swiggy;

import com.swiggy.controller.RestaurantController;
import com.swiggy.model.Restaurant;

public class RestaurantInitialisation {

    private static final RestaurantController RESTAURANT_CONTROLLER_INSTANCE = RestaurantController.getInstance();

    private static RestaurantInitialisation instance;

    private RestaurantInitialisation() {
    }

    public static RestaurantInitialisation getInstance() {
        if (instance == null) {
            instance = new RestaurantInitialisation();
        }

        return instance;
    }

    public void intializeRestaurants() {
        Restaurant kasali = new Restaurant("kasali");
        Restaurant rusi = new Restaurant("rusi");
        Restaurant nila = new Restaurant("nila");

        RESTAURANT_CONTROLLER_INSTANCE.setRestaurants(kasali.getId(), kasali);
        RESTAURANT_CONTROLLER_INSTANCE.setRestaurants(rusi.getId(), rusi);
        RESTAURANT_CONTROLLER_INSTANCE.setRestaurants(nila.getId(), nila);

        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Chicken Briyani", 100, "NonVeg", false, kasali);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Parotta", 15, "Veg", true, kasali);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Meals", 120, "Veg", true, kasali);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Chicken Rice", 90, "NonVeg", false, kasali);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Chicken Noodles", 80, "NonVeg", false, kasali);

        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Briyani", 80, "Veg", true, nila);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Parotta", 10, "Veg", true, nila);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Fish Meals", 80, "NonVeg", false, nila);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Egg Rice", 60, "NonVeg", false, nila);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Noodles", 75, "Veg", true, nila);

        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Mutton Briyani", 150, "NonVeg", false, rusi);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Parotta", 12, "Veg", true, rusi);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Meals", 110, "Veg", true, rusi);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Chicken Rice", 80, "NonVeg", false, rusi);
        RESTAURANT_CONTROLLER_INSTANCE.setFoodToMenuCard("Egg Noodles", 70, "NonVeg", false, rusi);

        kasali.setMenuCard();
        nila.setMenuCard();
        rusi.setMenuCard();

    }
}
