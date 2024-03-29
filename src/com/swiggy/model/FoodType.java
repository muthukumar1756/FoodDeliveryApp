package com.swiggy.model;

/**
 * <p>
 * Provides the food type.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum FoodType {
    VEG(1),
    NONVEG(2);

    private final int id;

    FoodType(final int id) {
        this.id = id;
    }

    public static FoodType getById(final int id) {
        for (FoodType type : values()) {

            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    public static int getId(FoodType foodType) {
        return foodType.id;
    }
}
