package com.swiggy.view.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {

    private static UserDataValidator instance;
    private static final String USER_NAME_PATTERN = "^(?!.*[._]{2})[A-Za-z][A-Za-z\\d_.]{0,28}[A-Za-z\\d]$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    private static final String PHONE_NUMBER_PATTERN = "^(0/91)?[6789]\\d{9}$";
    private static final String EMAIL_PATTERN = "^[a-z][a-z\\d._]+@[a-z]{5,}.[a-z]{2,3}$";
    private static final Pattern BACK_BUTTON_PATTERN = Pattern.compile("\\*");
    private static final Pattern FILTER_PATTERN = Pattern.compile("\\#");

    private UserDataValidator() {
    }

    public static UserDataValidator getInstance() {
        if (instance == null) {
            instance = new UserDataValidator();
        }

        return instance;
    }

    public boolean validateBackOption(final String back) {
        Matcher matcher = BACK_BUTTON_PATTERN.matcher(back);
        return matcher.find();
    }

    public boolean validateUserName(final String userName) {
       return userName.matches(USER_NAME_PATTERN);
    }

    public boolean validatePhoneNumber(final String phoneNumber) {
       return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    public boolean validateEmailId(final String emailId) {
        return emailId.matches(EMAIL_PATTERN);
    }

    public boolean validatePassword(final String password) {
        return password.matches(PASSWORD_PATTERN);
    }
}
