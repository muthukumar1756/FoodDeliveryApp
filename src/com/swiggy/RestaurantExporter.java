//package com.swiggy;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//
//import com.swiggy.controller.RestaurantController;
//import com.swiggy.model.Restaurant;
//
//public class RestaurantExporter {
//    private static final RestaurantController RESTAURANT_CONTROLLER = RestaurantController.getInstance();
//    private static final Map<Integer, Restaurant> RESTAURANTS = RESTAURANT_CONTROLLER.getRestaurantsList();
//
//
//    public static void main(String[] args) {
//        RestaurantInitializator.intializeRestaurants();
//
//        Properties properties = new Properties();
//
//        addRestaurantToProperties(properties, "kasali");
//        addRestaurantToProperties(properties, "rusi");
//        addRestaurantToProperties(properties, "nila");
//
//        savePropertiesToFile(properties, "restaurants.properties");
//
//
//    }
//
//    private static void addRestaurantToProperties(Properties properties, String restaurantName) {
//        final int restaurantId = getRestaurantIdByName(restaurantName);
//            Restaurant restaurant = RESTAURANTS.get(restaurantId);
//            properties.setProperty(String.valueOf(restaurantId), restaurant.toString());
//    }
//
//    private static int getRestaurantIdByName(String restaurantName) {
//        for (Map.Entry<Integer, Restaurant> entry : RESTAURANTS.entrySet()) {
//            if (entry.getValue().getName().equalsIgnoreCase(restaurantName)) {
//                return entry.getValue().getId();
//            }
//        }
//        return 0;
//    }
//
//    private static void savePropertiesToFile(Properties properties, String fileName) {
//        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
//            properties.store(fileOutputStream, "Restaurant Information");
//            System.out.println("Properties saved to " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
