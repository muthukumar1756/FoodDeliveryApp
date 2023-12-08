package swiggy.service;

import swiggy.model.User;
import swiggy.view.RestaurantView;
import swiggy.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static UserService instance;
    private static final RestaurantView RESTAURANT_VIEW_INSTANCE = RestaurantView.getInstance();
    private User currentUser;
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

    public void storeUserSignupDetails(String userName, String phoneNumber, String emailId, String password) {
        users.put(phoneNumber, new User(userName, phoneNumber, emailId, password));
        USER_VIEW_INSTANCE.loginDisplay();
    }

    public void checkUserLoginValidation(String phoneNumber, String password) {
        if (users.containsKey(phoneNumber)) {
            currentUser = users.get(phoneNumber);
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
