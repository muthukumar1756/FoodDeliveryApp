package swiggy.view.validation;

public class UserValidation {

    private static UserValidation instance;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    private static final String PHONE_NUMBER_PATTERN = "^[6-9]\\d{9}$";
    private static final String EMAIL_PATTERN = "^[a-z][a-z\\d._]+@[a-z]{5,}.[a-z]{2,3}$";

    private UserValidation() {
    }

    public static UserValidation getInstance() {
        if (instance == null) {
            instance = new UserValidation();
        }

        return instance;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
       return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    public boolean validateEmailId(String emailId) {
        return emailId.matches(EMAIL_PATTERN);
    }

    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }
}
