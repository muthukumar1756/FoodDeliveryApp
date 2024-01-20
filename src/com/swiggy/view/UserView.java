package com.swiggy.view;

import com.swiggy.controller.UserController;
import com.swiggy.model.User;
import com.swiggy.model.PasswordGenerator;
import com.swiggy.view.validation.UserDataValidator;

/**
 * <p>
 *  Handles user creation, authentication and updates.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserView extends CommonView{

    private static UserView userView;

    private final UserController userController;
    private final UserDataValidator userDataValidator;

    private UserView() {
        userController = UserController.getInstance();
        userDataValidator = UserDataValidator.getInstance();
        PasswordGenerator passwordGenerator = PasswordGenerator.getInstance();
    }

    /**
     * <p>
     * Gets the object of the user view class.
     * </p>
     *
     * @return The user view object
     */
    public static UserView getInstance() {
        if (null == userView) {
            userView = new UserView();
        }

        return userView;
    }

    /**
     * <p>
     * Displays the main menu and gets the user choice for signup or login.
     * </p>
     */
    public void displayMainMenu() {
        printMessage("1.Signup\n2.Login\n3.Exit");
        final int userChoice = getChoice();

        switch (userChoice) {
            case 1:
                signUp();
                break;
            case 2:
                login();
                break;
            case 3:
                exit();
                break;
            default:
                printMessage("Invalid UserChoice");
                displayMainMenu();
        }
    }

    /**
     * <p>
     * Handles the user signup process.
     * </p>
     */
    private void signUp() {
        printMessage("User Signup Or Enter * To Go Back");
        final User user = new User(getName(), getPhoneNumber(), getEmailId(), getPassword());

        if (userController.createUser(user)) {
            displayHomePageMenu(user);
        } else {
            printMessage("The User Already Exists");
            displayMainMenu();
        }
    }

    /**
     * <p>
     * Gets the username from the user after validating the name.
     * </p>
     *
     * @return The valid username of the user
     */
    private String getName() {
        printMessage("Enter Your Name");
        final String name = getInfo();

        if ("back".equals(name)) {
            displayMainMenu();
        }

        if (!userDataValidator.validateUserName(name)) {
            printMessage("Enter A Valid User Name");
            getName();
        }

        return name;
    }

    /**
     * <p>
     * Gets the valid mobile number from the user after validation.
     * </p>
     *
     * @return The mobile number of the user
     */
    private String getPhoneNumber() {
        printMessage("Enter Your Phone Number");
        final String phoneNumber = getInfo();

        if ("back".equals(phoneNumber)) {
            displayMainMenu();
        }

        if (!userDataValidator.validatePhoneNumber(phoneNumber)) {
            printMessage("Enter A Valid Phone Number");
            getPhoneNumber();
        }

        return phoneNumber;
    }

    /**
     * <p>
     * Gets the valid email from the user after validation.
     * </p>
     *
     * @return The valid email of the user
     */
    private String getEmailId() {
        printMessage("Enter Your EmailId");
        final String emailId = getInfo();

        if ("back".equals(emailId)) {
            displayMainMenu();
        }

        if (!userDataValidator.validateEmailId(emailId)) {
            printMessage("Enter A Valid EmailId");
            getEmailId();
        }

        return emailId;
    }

    /**
     * <p>
     * Gets the password from the user after validating the password.
     * </p>
     *
     * @return The validated password of the user
     */
    private String getPassword() {
        printMessage("Enter Your Password");
        final String password = getInfo();

        if ("back".equals(password)) {
            displayMainMenu();
        }

        if (!userDataValidator.validatePassword(password)) {
            printMessage("Enter A Valid Password");
            getPassword();
        }

        return password;
    }

    /**
     * <p>
     * Gets the user details for login process.
     * </p>
     */
     private void login() {
        final String phoneNumber = getPhoneNumber();

         if (userController.isUserExist(phoneNumber)) {
             final User currentUser = userController.getUser(phoneNumber, getPassword());

             if (null == currentUser) {
                 printMessage("Incorrect Password");
                 login();
             }
             displayHomePageMenu(currentUser);
         } else {
             printMessage("The Entered Phone Number Is Not Registered!");
             login();
         }
    }

    /**
     * <p>
     * Displays restaurants or allows the user to update their profile based on the users input.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    public void displayHomePageMenu(final User user) {
        printMessage("To Go Back Enter *\n1.Display Restaurants\n2.Edit User Profile\n3.Logout");
        final int userChoice = getChoice();

        if (-1 == userChoice) {
            displayMainMenu();
        }

        switch (userChoice) {
            case 1:
                RestaurantView.getInstance().displayRestaurants(user);
                break;
            case 2:
                updateUser(user);
                break;
            case 3:
                displayMainMenu();
                break;
            default:
                printMessage("Enter A Valid Option");
                displayHomePageMenu(user);
        }
    }

    /**
     * <p>
     * Updates the users information based on the chosen option.
     * </p>
     *
     * @param user Represents the current {@link User}
     */
    private void updateUser(final User user) {
        printMessage("To Go Back Enter *\n1.Update EmailId\n2.Update Phone Number\n3.Update Password\n4.Update User Name");
        final int choice = getChoice();

        if (-1 == choice) {
            displayHomePageMenu(user);
        }

        switch (choice) {
            case 1:
                userController.updateEmailId(user, getEmailId());
                break;
            case 2:
                userController.updatePhoneNumber(user, getPhoneNumber());
                break;
            case 3:
                userController.updatePassword(user, getPassword());
                break;
            case 4:
                userController.updateUserName(user, getName());
                break;
            default:
                printMessage("Enter A Valid Option");
                updateUser(user);
        }
        updateUser(user);
    }

    /**
     * <p>
     * Exits from the application.
     * </p>
     */
    private void exit() {
        System.exit(0);
    }
}
