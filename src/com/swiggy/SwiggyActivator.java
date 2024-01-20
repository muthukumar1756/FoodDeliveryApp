package com.swiggy;

import com.swiggy.view.UserView;

/**
 * <p>
 * Activates the features of the swiggy application
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
class SwiggyActivator {

    /**
     * <p>
     * Starts the execution of swiggy application.
     * </p>
     *
     * @param args Represents command line arguments
     */
    public static void main(final String[] args) {
        RestaurantInitializer.getInstance().createRestaurants();
        System.out.println("Welcome To Swiggy");
        UserView.getInstance().displayMainMenu();
    }
}
