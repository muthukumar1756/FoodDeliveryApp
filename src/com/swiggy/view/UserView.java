package com.swiggy.view;

import com.swiggy.controller.UserController;
import com.swiggy.model.User;
import com.swiggy.view.validation.UserDataValidator;

import java.util.Scanner;

public class UserView {

    private static UserView instance;
    private static final Scanner SCANNER = ScannerInstance.getInstance();
    private static final RestaurantView RESTAURANT_VIEW = RestaurantView.getInstance();
    private static final UserController USER_CONTROLLER = UserController.getInstance();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();

    private UserView() {
    }

    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }

        return instance;
    }

    public void printMainMenu() {
        System.out.println("1.Signup\n2.Login");
        final int UserChoice = Integer.parseInt(SCANNER.nextLine().trim());

        switch (UserChoice) {
            case 1:
                signUp();
                break;
            case 2:
                login();
                break;
            default:
                System.out.println("Invalid UserChoice");
                printMainMenu();
        }
    }

    private void signUp() {
        System.out.println("User Signup Or Enter * To Go Back");
        String userName = getName();
        String phoneNumber = getPhoneNumber();
        String emailId = getEmailId();
        String password = getPassword();

        USER_CONTROLLER.setUserData(userName, phoneNumber, emailId, password);
        if (USER_CONTROLLER.setUsersData(userName, phoneNumber, emailId, password)) {
         login();
        }

    }

    private String getName() {
        System.out.println("Enter Your Name");
        final String name = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(name)) {
            printMainMenu();
        }

        if (!USER_DATA_VALIDATOR.validatePhoneNumber(name)) {
            System.out.println("Enter A Valid User Name");
            getName();
        }

        return name;
    }

    private String getPhoneNumber() {
        System.out.println("Enter Your Phone Number");
        final String phoneNumber = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(phoneNumber)) {
            printMainMenu();
        }

//        if (!USER_DATA_VALIDATOR.validatePhoneNumber(phoneNumber)) {
//            System.out.println("Enter A Valid Phone Number");
//            getPhoneNumber();
//        }
//
//        if (USER_CONTROLLER.verifyExistingUser(phoneNumber)) {
//            System.out.println("Phone Number Already Exists");
//            getPhoneNumber();
//        }

            return phoneNumber;
    }

    private String getEmailId() {
        System.out.println("Enter Your EmailId");
        final String emailId = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(emailId)) {
            printMainMenu();
        }

//        if (!USER_DATA_VALIDATOR.validateEmailId(emailId)) {
//            System.out.println("Enter A Valid EmailId");
//            getEmailId();
//        }

        return emailId;
    }

    private String getPassword() {
        System.out.println("Enter Your Password");
        final String password = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(password)) {
            printMainMenu();
        }

//        if (!USER_DATA_VALIDATOR.validatePassword(password)) {
//            System.out.println("Enter A Valid Password");
//            getPassword();
//        }

        return password;
    }

     public void login() {
        System.out.println("User Login Or To Go Back Enter *:\nEnter The PhoneNumber");
        final String phoneNumber = SCANNER.nextLine().trim();

         if (USER_DATA_VALIDATOR.validateBackOption(phoneNumber)) {
             printMainMenu();
         }
        System.out.println("Enter The Password");
        final String password = SCANNER.nextLine().trim();

         if (USER_DATA_VALIDATOR.validateBackOption(password)) {
             printMainMenu();
         }
        USER_CONTROLLER.verifyUserLogin(phoneNumber, password);
    }
    
    public void handlePhoneNumberNotFound() {
        System.out.println("The Entered Phone Number Is Not Registered!");
        login();
    }

    public void handlePasswordMismatch() {
        System.out.println("Password Doesn't Match Try Again");
        login();
    }

    public void displayUserOptions(final User currentUser) {
        System.out.println("To Go Back Enter *\n1.Display Restaurants\n2.Edit User Profile\n3.Logout");
        final String userChoice = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(userChoice)) {
            printMainMenu();
        }

        switch (Integer.parseInt(userChoice)) {
            case 1:
                RESTAURANT_VIEW.displayRestaurants(currentUser);
                break;
            case 2:
                editUserProfile(currentUser);
                break;
            case 3:
                printMainMenu();
                break;
            default:
                System.out.println("Enter A Valid Option");
                displayUserOptions(currentUser);
        }
    }

    private void editUserProfile(final User currentUser) {
        System.out.println("To Go Back Enter *\n1.To Edit The EmailId\n2.To Edit The Phone Number");
        final String editProfileChoice = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(editProfileChoice)) {
            displayUserOptions(currentUser);
        }

        switch (Integer.parseInt(editProfileChoice)) {
            case 1:
                editEmailId(currentUser);
                break;
            case 2:
                editPhoneNumber(currentUser);
                break;
            default:
                System.out.println("Enter A Valid Option");
                editUserProfile(currentUser);
        }
    }

    private void editPhoneNumber(final User currentUser) {
        System.out.println("Enter Your PhoneNumber");
        final String phoneNumber = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(phoneNumber)) {
            editUserProfile(currentUser);
        }

        if (USER_DATA_VALIDATOR.validatePhoneNumber(phoneNumber)) {
            USER_CONTROLLER.editUserPhoneNumber(currentUser, phoneNumber);
        } else {
            System.out.println("Enter A Valid Phone Number");
            editPhoneNumber(currentUser);
        }
    }

    private void editEmailId(final User currentUser) {
        System.out.println("Enter Your EmailId");
        final String emailId = SCANNER.nextLine().trim();

        if (USER_DATA_VALIDATOR.validateBackOption(emailId)) {
            editUserProfile(currentUser);
        }

        if (USER_DATA_VALIDATOR.validateEmailId(emailId)) {
                USER_CONTROLLER.editUserEmailId(currentUser, emailId);
        } else {
            System.out.println("Enter A Valid EmailId");
            editEmailId(currentUser);
        }
    }
}
