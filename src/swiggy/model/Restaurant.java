package swiggy.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final String NAME;
    private final int ID;
    private static int idCount;

    public List<Food> menuCard = new ArrayList<>();

    public Restaurant(String NAME) {
        this.NAME = NAME;
        this.ID = ++idCount;
    }

    public String getName() {
        return NAME;
    }

    public int getId() {
        return ID;
    }

    public void addFoodToMenu(String name, int rate, String type) {
        menuCard.add(new Food(name, rate, type));
    }

    public List<Food> getMenuCardList(){
        return menuCard;
    }
}
