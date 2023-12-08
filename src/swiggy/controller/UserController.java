package swiggy.controller;

import swiggy.service.UserService;

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

    public void setUserSignupDetails(String userName, String phoneNumber, String emailId, String password) {
        USER_SERVICE_INSTANCE.storeUserSignupDetails(userName, phoneNumber, emailId, password);
    }

    public void userLoginValidation (String phoneNumber, String password) {
        USER_SERVICE_INSTANCE.checkUserLoginValidation(phoneNumber, password);
    }
}
