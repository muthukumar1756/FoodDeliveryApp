package swiggy.model;

public class Food {

    private final int FOOD_ID;
    private static int foodIdCount;
    private final String NAME;
    private int rate;
    private String type;

    public Food(String name, int rate, String type) {
        this.NAME = name;
        this.rate = rate;
        this.type = type;
        this.FOOD_ID = ++foodIdCount;
    }

    public String getName() {
        return NAME;
    }

    public int getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }

    public int getFoodId() {
        return FOOD_ID;
    }
}
