package com.swiggy.view;

import com.swiggy.controller.UserController;
import com.swiggy.service.UserService;
import com.swiggy.view.validation.UserValidation;

import java.util.Scanner;

public class UserView {

    private static UserView instance;
    private static final UserController USER_CONTROLLER_INSTANCE = UserController.getInstance();
    private static final UserValidation USER_VALIDATION_INSTANCE = UserValidation.getInstance();
    private static final UserService USER_SERVICE_INSTANCE = UserService.getInstance();

    static Scanner scanner = new Scanner(System.in);

    private UserView() {
    }

    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }

        return instance;
    }

    public void printMenu() {
        System.out.println("welcome to Swiggy");
        System.out.println("1.Signup");
        System.out.println("2.Login");
        getOption();
    }

    public void getOption() {
        int loggingOption = scanner.nextInt();

        switch (loggingOption) {
            case 1:
                getSignupDetails();
                break;
            case 2:
                getLoginDetails();
                break;
            default:
                System.out.println("Invalid loggingOption");
                printMenu();
        }
    }

    public void getSignupDetails () {
        System.out.println("User signup");
        System.out.println("Enter your name");
        final String userName = scanner.next();

        scanner.nextLine();
        System.out.println("Enter your phoneNumber");
        final String phoneNumber = scanner.next();

        if (USER_SERVICE_INSTANCE.checkUserIsAlreadyExist(phoneNumber)) {
            System.out.println("Enter your EmailId");
            final String emailId = scanner.next();

            System.out.println("Enter your Password");
            final String password = scanner.next();

//            if (USER_VALIDATION_INSTANCE.validatePhoneNumber(phoneNumber)) {
//
//                if (USER_VALIDATION_INSTANCE.validateEmailId(emailId)) {
//
//                    if (USER_VALIDATION_INSTANCE.validatePassword(password)) {
            USER_CONTROLLER_INSTANCE.setUserSignupDetails(userName, phoneNumber, emailId, password);
//                    } else {
//                        System.out.println("Invalid password");
//                        signupDetails();
//                    }
//                } else {
//                    System.out.println("Invalid Email id");
//                    signupDetails();
//                }
//            } else {
//                System.out.println("Invalid Phone Number");
//                signupDetails();
//            }
        } else {
            System.out.println("User is already exist");
            printMenu();
        }
    }

    public void getLoginDetails () {
        System.out.println("User Login:");
        System.out.println("Enter the PhoneNumber");
        String phoneNumber = scanner.next();

        System.out.println("Enter the password");
        String password = scanner.next();

        USER_CONTROLLER_INSTANCE.userLoginValidation(phoneNumber, password);
    }
    
    public void phoneNumberNotFound() {
        System.out.println("The Entered Phone Number is Not Registered! Try these options");
        System.out.println("1.Enter phone number again");
        System.out.println("2.Signup");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                getLoginDetails();
                break;
            case 2:
                getSignupDetails();
                break;
            default:
                System.out.println("Enter a valid option");
                phoneNumberNotFound();
        }
    }

    public void passwordNotMatch(){
        System.out.println("Password doesn't match try again");
        getLoginDetails();
    }
}
