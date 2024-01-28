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

    /**
     * <p>
     * Gets the food category with the id.
     * </p>
     *
     * @param id Represents the id of the food category
     * @return The category type if the id matches, null otherwise
     */
    public static FoodType getById(final int id) {
        for (FoodType type : values()) {

            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    /**
     *
     * @param foodType Represents the category of the food
     * @return The id of the food category
     */
    public static int getId(FoodType foodType) {
        return foodType.id;
    }
}
