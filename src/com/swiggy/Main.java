package com.swiggy;

import com.swiggy.view.UserView;

public class Main {

    public static void main(final String[] arguments) {
        RestaurantInitializator.getInstance().setApp();
        System.out.println("Welcome To Swiggy");
        UserView.getInstance().printMainMenu();
    }
}
