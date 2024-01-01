package com.swiggy.controller;

import com.swiggy.model.User;
import com.swiggy.service.UserService;
import com.swiggy.service.impl.UserServiceImpl;

public class UserController {

    private static UserController instance;
    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }

        return instance;
    }

    public void setUserData(final String userName, final String phoneNumber, final String emailId, final String password) {
        USER_SERVICE.setUser(userName, phoneNumber, emailId, password);
    }
    public boolean setUsersData(final String userName, final String phoneNumber, final String emailId, final String password) {
        return USER_SERVICE.setUsers(userName, phoneNumber, emailId, password);
    }

    public void verifyUserLogin(final String phoneNumber, final String password) {
        USER_SERVICE.handleLoginValidation(phoneNumber, password);
    }

    public boolean verifyExistingUser(final String phoneNumber) {
        return USER_SERVICE.isUserExist(phoneNumber);
    }

    public void editUserPhoneNumber(final User currentuser, final String phoneNumber) {
        USER_SERVICE.setUserPhoneNumber(currentuser, phoneNumber);
    }
    public void editUserEmailId(final User currentuser, final String emailId) {
        USER_SERVICE.setUserEmailId(currentuser, emailId);
    }

}
