package com.swiggy.controller;

import com.swiggy.service.UserService;

public class UserController {

    private static UserController instance;
    private static final UserService USER_SERVICE_INSTANCE = UserService.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }

        return instance;
    }

    public void setUserSignupDetails(final String userName, final String phoneNumber, final String emailId, final String password) {
        USER_SERVICE_INSTANCE.storeUserSignupDetails(userName, phoneNumber, emailId, password);
    }

    public void userLoginValidation(final String phoneNumber, final String password) {
        USER_SERVICE_INSTANCE.checkUserLoginValidation(phoneNumber, password);
    }
}
