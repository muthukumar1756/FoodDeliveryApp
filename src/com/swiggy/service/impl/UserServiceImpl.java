package com.swiggy.service.impl;

import com.swiggy.model.User;
import com.swiggy.service.UserService;
import com.swiggy.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static UserService instance;
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final Map<String, User> users = new HashMap<>();

    private UserServiceImpl () {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }

    public void setUser(final String userName, final String phoneNumber, final String emailId, final String password) {
        users.put(phoneNumber, new User(userName, phoneNumber, emailId, password));
        USER_VIEW.login();
    }

    public boolean setUsers(final String userName, final String phoneNumber, final String emailId, final String password) {
        if (users.containsKey(phoneNumber)) {
            return false;
        } else {
            users.put(phoneNumber, new User(userName, phoneNumber, emailId, password));

            return true;
        }
    }

    public boolean isUserExist(final String phoneNumber) {
        return users.containsKey(phoneNumber);
    }

    public void handleLoginValidation(final String phoneNumber, final String password) {
        if (users.containsKey(phoneNumber)) {
            final User currentUser = users.get(phoneNumber);
            final String currentUserPassword = currentUser.getPassword();

            if (password.equals(currentUserPassword)) {
                USER_VIEW.displayUserOptions(currentUser);
            } else {
                USER_VIEW.handlePasswordMismatch();
            }
        } else {
            USER_VIEW.handlePhoneNumberNotFound();
        }
    }

    public void setUserEmailId(User currentUser, String emailId) {
        currentUser.setEmailId(emailId);
    }

    public void setUserPhoneNumber(User currentUser, String phoneNumber) {
        currentUser.setPhoneNumber(phoneNumber);
    }
}
