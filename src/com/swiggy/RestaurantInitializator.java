package com.swiggy;

import com.swiggy.controller.RestaurantController;
import com.swiggy.model.Food;
import com.swiggy.model.Restaurant;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RestaurantInitializator {

    private static final RestaurantController RESTAURANT_CONTROLLER = RestaurantController.getInstance();

    private static RestaurantInitializator instance;

    private RestaurantInitializator () {
    }

    public static RestaurantInitializator getInstance() {
        if (instance == null) {
            instance = new RestaurantInitializator();
        }

        return instance;
    }


//    public void setRestaurants() {
//        final Map<Integer, Restaurant> restaurants = new HashMap<>();
//        String path = "D:\\java\\Projects java\\FoodDeliveryApp\\restaurants";
//
//        loadRestaurants(path, restaurants);
//    }
//
//    public static void loadRestaurants(final String path, final Map<Integer, Restaurant> restaurants) {
//        try {
//            FileReader fileReader = new FileReader(path);
//            Properties properties = new Properties();
//
//            properties.load(fileReader);
//            for (final Object key: properties.keySet()) {
//                String value = properties.getProperty(String.valueOf(key));
//                Restaurant restaurant = new Restaurant(value);
//
//                RESTAURANT_CONTROLLER.setRestaurants(restaurant.getId(), restaurant);
//                restaurant.setMenuCard();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void setApp() {
//        final List<Food> menuCard = new ArrayList<>();
//        String path1 = "D:\\java\\Projects java\\FoodDeliveryApp\\kasali.txt";
//        String path2 = "D:\\java\\Projects java\\FoodDeliveryApp\\nila.txt";
//        String path3 = "D:\\java\\Projects java\\FoodDeliveryApp\\rusi.txt";
//
//        loadApp(path1, menuCard);
//        loadApp(path2, menuCard);
//        loadApp(path3, menuCard);
//    }
//
//    public static void loadApp(final String path, final List<Food> menuCard) {
//        try {
//            FileReader fileReader = new FileReader(path);
//            Properties properties = new Properties();
//
//            properties.load(fileReader);
//
//            for (final Object key: properties.keySet()) {
//                String value = properties.getProperty(String.valueOf(key));
//                String[] restaurantProperty = value.split(",");
//
//                String name = restaurantProperty[0];
//                int rate = Integer.parseInt(restaurantProperty[1]);
//                String type = restaurantProperty[2];
//                boolean isVeg = Boolean.parseBoolean(restaurantProperty[3]);
//                int foodQuantity = Integer.parseInt(restaurantProperty[4]);
//                String restaurantName = restaurantProperty[5];
//                Restaurant restaurant = new Restaurant(restaurantName);
//
//                RESTAURANT_CONTROLLER.setFoodToMenuCard(name, rate, type, isVeg, foodQuantity, restaurant);
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void intializeRestaurants() {
//
//        Restaurant kasali = new Restaurant("kasali");
//        Restaurant rusi = new Restaurant("rusi");
//        Restaurant nila = new Restaurant("nila");
//
//        RESTAURANT_CONTROLLER.setRestaurants(kasali.getId(), kasali);
//        RESTAURANT_CONTROLLER.setRestaurants(rusi.getId(), rusi);
//        RESTAURANT_CONTROLLER.setRestaurants(nila.getId(), nila);
//
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Chicken Briyani", 100, "NonVeg", false, 5, kasali);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Parotta", 15, "Veg", true, 5, kasali);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Meals", 120, "Veg", true, 5, kasali);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Chicken Rice", 90, "NonVeg", false, 5, kasali);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Chicken Noodles", 80, "NonVeg", false, 5, kasali);
//
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Briyani", 80, "Veg", true, 5, nila);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Parotta", 10, "Veg", true, 5, nila);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Fish Meals", 80, "NonVeg", false, 5, nila);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Egg Rice", 60, "NonVeg", false, 5, nila);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Noodles", 75, "Veg", true, 5, nila);
//
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Mutton Briyani", 150, "NonVeg", false, 5, rusi);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Parotta", 12, "Veg", true, 5, rusi);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Meals", 110, "Veg", true, 5, rusi);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Chicken Rice", 80, "NonVeg", false, 5, rusi);
//        RESTAURANT_CONTROLLER.setFoodToMenuCard("Egg Noodles", 70, "NonVeg", false, 5, rusi);
//
//        kasali.setMenuCard();
//        nila.setMenuCard();
//        rusi.setMenuCard();
//    }
}
