package com.swiggy.launcher;

import org.apache.log4j.Logger;

import com.swiggy.view.UserView;
import org.apache.log4j.PropertyConfigurator;

/**
 * <p>
 * Activates the features of the swiggy application
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class SwiggyActivator {

    /**
     * <p>
     * Starts the execution of swiggy application.
     * </p>
     *
     * @param args Represents command line arguments
     */
    public static void main(final String[] args) {
        RestaurantInitializer.getInstance().loadRestaurants();
        PropertyConfigurator.configure("src/com/swiggy/resources/log4j.properties");
        Logger.getLogger(SwiggyActivator.class).info("Welcome To Swiggy");
        UserView.getInstance().displayMainMenu();
    }
}