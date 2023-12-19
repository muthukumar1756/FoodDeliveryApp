package com.swiggy.service;

import com.swiggy.model.User;
import com.swiggy.view.RestaurantView;
import com.swiggy.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static UserService instance;
    private static final RestaurantView RESTAURANT_VIEW_INSTANCE = RestaurantView.getInstance();
    private static final UserView USER_VIEW_INSTANCE = UserView.getInstance();

    public static Map<String, User> users = new HashMap<>();

    private UserService () {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public void storeUserSignupDetails(final String userName, final String phoneNumber, final String emailId, final String password) {
        users.put(phoneNumber, new User(userName, phoneNumber, emailId, password));
        USER_VIEW_INSTANCE.getLoginDetails();
    }

    public boolean checkUserIsAlreadyExist(final String phoneNumber) {
        if (users.containsKey(phoneNumber)) {
            return false;
        } else {
            return true;
        }
    }

    public void checkUserLoginValidation(final String phoneNumber, final String password) {
        if (users.containsKey(phoneNumber)) {
            final User currentUser = users.get(phoneNumber);
            String currentUserPassword = currentUser.getPassword();

            if (password.equals(currentUserPassword)) {
                RESTAURANT_VIEW_INSTANCE.displayRestaurants(currentUser);
            } else {
                USER_VIEW_INSTANCE.passwordNotMatch();
            }
        } else {
            USER_VIEW_INSTANCE.phoneNumberNotFound();
        }
    }
}
