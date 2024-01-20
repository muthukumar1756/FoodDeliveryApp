package com.swiggy;

import com.swiggy.controller.RestaurantController;
import com.swiggy.customexception.FileAccessException;
import com.swiggy.customexception.FoodCreationException;
import com.swiggy.model.Food;
import com.swiggy.model.FoodType;
import com.swiggy.model.Restaurant;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * Initializes the restaurants and foods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
class RestaurantInitializer {

    private static RestaurantInitializer restaurantInitializer;

    private final RestaurantController restaurantController = RestaurantController.getInstance();
    private final String restaurantsPath;

    private RestaurantInitializer() {
        System.setProperty("RESTAURANTPATH", "D:\\java\\Projects java\\FoodDeliveryApp\\Restaurants\\");
        restaurantsPath = System.getProperty("RESTAURANTPATH");
    }

    public static RestaurantInitializer getInstance() {
        if (null == restaurantInitializer) {
            restaurantInitializer = new RestaurantInitializer();
        }

        return restaurantInitializer;
    }

    /**
     * <p>
     * Creates restaurants.
     * </p>
     */
    public void createRestaurants() {
        final String path = String.join("", restaurantsPath, "Restaurants.properties");

        try (final FileReader fileReader = new FileReader(path)) {
            final Properties properties = new Properties();
            final Map<Integer, Restaurant> restaurants = new HashMap<>();

            properties.load(fileReader);

            for (final Object key : properties.keySet()) {
                final int id = Integer.parseInt((String) key);
                final String name = properties.getProperty((String) key);
                final Restaurant restaurant = new Restaurant(name);

                restaurants.put(id, restaurant);
            }
            restaurantController.createRestaurants(restaurants);
            loadFoodsFromRestaurant(restaurants);
        } catch (IOException message) {
            throw new FileAccessException(message.getMessage());
        }
    }

    /**
     * <p>
     * loads foods from restaurant.
     * </p>
     */
    public void loadFoodsFromRestaurant(final Map<Integer, Restaurant> restaurants) {
        for (final int restaurantId : restaurants.keySet()) {
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            final Map <String, String> env = processBuilder.environment();

//            environment.put("RESTAURANTPATH", "D:\\java\\Projects java\\FoodDeliveryApp\\Restaurants\\");
//            final String restaurantPath = String.join("", System.getenv("RESTAURANTPATH"),
//                    restaurants.get(restaurantId).getName().toLowerCase() + ".properties");
            final String restaurantPath = String.join("", restaurantsPath,
                    restaurants.get(restaurantId).getName().toLowerCase() + ".properties");
            final Restaurant restaurant = restaurants.get(restaurantId);

            createFoods(restaurantPath, restaurant);
        }
    }

    /**
     * <p>
     * Creates food objects from loaded restaurant paths.
     * </p>
     *
     * @param path Represents the path of the restaurant where food items are stored
     * @param restaurant Represents the current {@link Restaurant}
     */
    public void createFoods(final String path, final Restaurant restaurant) {
        try (final FileReader fileReader = new FileReader(path)) {
            final Properties properties = new Properties();

            properties.load(fileReader);

            for (final Object key : properties.keySet()) {
                final String value = properties.getProperty(String.valueOf(key));
                final String[] restaurantProperty = value.split(",");
                final String name = restaurantProperty[0];
                final int rate = Integer.parseInt(restaurantProperty[1]);
                final String type = restaurantProperty[2];
                final int foodQuantity = Integer.parseInt(restaurantProperty[3]);

                if (type.equalsIgnoreCase(FoodType.VEG.name())) {
                    restaurantController.createVegFood(new Food(name, rate, FoodType.VEG, foodQuantity), restaurant);
                } else {
                    restaurantController.createNonVegFood(new Food(name, rate, FoodType.NONVEG, foodQuantity), restaurant);
                }
            }
            restaurant.createMenuCard();
        } catch (IOException message) {
            throw new FoodCreationException(message.getMessage());
        }
    }
}
