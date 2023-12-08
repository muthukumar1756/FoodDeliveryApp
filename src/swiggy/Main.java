package swiggy;

import swiggy.model.Restaurant;
import swiggy.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<Integer, Restaurant> restaurants = new HashMap<> ();

     private static void intializeRestaurants() {
        Restaurant kasali = new Restaurant("kasali");
        Restaurant rusi = new Restaurant("rusi");
        Restaurant nila = new Restaurant("nila");

        restaurants.put(kasali.getId(), kasali);
        restaurants.put(rusi.getId(), rusi);
        restaurants.put(nila.getId(), nila);

        kasali.addFoodToMenu("Chicken Briyani", 100, "NonVeg");
        kasali.addFoodToMenu("Parotta", 15, "Veg");
        kasali.addFoodToMenu("Meals", 120, "Veg");
        kasali.addFoodToMenu("Chicken Rice", 90, "NonVeg");
        kasali.addFoodToMenu("Chicken Noodles", 80, "NonVeg");

        nila.addFoodToMenu("Briyani", 80, "Veg");
        nila.addFoodToMenu("Parotta", 10, "Veg");
        nila.addFoodToMenu("Fish Meals", 80, "NonVeg");
        nila.addFoodToMenu("Egg Rice", 60, "NonVeg");
        nila.addFoodToMenu("Noodles", 75, "Veg");

        rusi.addFoodToMenu("Mutton Briyani", 150, "NonVeg");
        rusi.addFoodToMenu("Parotta", 12, "Veg");
        rusi.addFoodToMenu("Meals", 110, "Veg");
        rusi.addFoodToMenu("Chicken Rice", 80, "NonVeg");
        rusi.addFoodToMenu("Egg Noodles", 70, "NonVeg");
    }

    public static void main(String[] args) {
        intializeRestaurants();
        UserView.getInstance().printMenu();
    }
}
