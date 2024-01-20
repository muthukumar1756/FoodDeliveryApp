package com.swiggy.view;

import com.swiggy.view.validation.UserDataValidator;

import java.util.Scanner;

/**
 * <p>
 * Represents the commonly used field and methods in the application
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class CommonView {

    private static Scanner scanner;

    private final UserDataValidator userDataValidator;

    CommonView() {
        userDataValidator = UserDataValidator.getInstance();
    }

    /**
     * <p>
     * Gets the scanner object.
     * </p>
     *
     * @return The scanner object
     */
    public static Scanner getScannerInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner;
    }

    /**
     * <p>
     * Prints the display messages.
     * </p>
     *
     * @param message Represents information of the process
     */
    public void printMessage(final String message) {
        System.out.println(message);
    }

    /**
     * <p>
     * Gets the choice given by the user.
     * </p>
     *
     * @return The choice of the user
     */
    public int getChoice(){
        final String choice = getScannerInstance().nextLine().trim();

        if (userDataValidator.isBackButton(choice)) {
            return -1;
        } else {
            try {
                return Integer.parseInt(choice);
            } catch (NumberFormatException message) {
                System.out.println("Enter valid input");

                return getChoice();
            }
        }
    }

    /**
     * <p>
     * Gets the information given by the user.
     * </p>
     *
     * @return The information given by the user
     */
    public String getInfo() {
        final String info = getScannerInstance().nextLine().trim();

        if (userDataValidator.isBackButton(info)) {
            return "back";
        } else {
            return info;
        }
    }
}
