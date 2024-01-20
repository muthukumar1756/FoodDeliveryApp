package com.swiggy.view.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Validates the input given by the users.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class UserDataValidator {

    private static UserDataValidator userDataValidator;

    private static final String USER_NAME_PATTERN = "^(?!.*[._]{2})[A-Za-z][A-Za-z\\d_.]{0,28}[A-Za-z\\d]$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    private static final String PHONE_NUMBER_PATTERN = "^(0/91)?[6789]\\d{9}$";
    private static final String EMAIL_PATTERN = "^[a-z][a-z\\d._]+@[a-z]{5,}.[a-z]{2,3}$";
    private static final Pattern BACK_BUTTON_PATTERN = Pattern.compile("\\*");

    private UserDataValidator() {
    }

    /**
     * <p>
     * Gets the object of the class.
     * </p>
     *
     * @return The validation object
     */
    public static UserDataValidator getInstance() {
        if (null == userDataValidator) {
            userDataValidator = new UserDataValidator();
        }

        return userDataValidator;
    }

    /**
     * <p>
     * Validates the user input for back option.
     * </p>
     *
     * @param back The back choice of the user
     * @return True if back condition is satisfied, false otherwise
     */
    public boolean isBackButton(final String back) {
        Matcher matcher = BACK_BUTTON_PATTERN.matcher(back);
        return matcher.find();
    }

    /**
     * <p>
     * Validates the username of the user.
     * </p>
     *
     * @param userName The username of the user
     * @return True if username is valid, false otherwise
     */
    public boolean validateUserName(final String userName) {
       return userName.matches(USER_NAME_PATTERN);
    }

    /**
     * <p>
     * Validates the mobile number of the user.
     * </p>
     *
     * @param phoneNumber The mobile number of the user
     * @return True if mobile number is valid, false otherwise
     */
    public boolean validatePhoneNumber(final String phoneNumber) {
       return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    /**
     * <p>
     * Validates the email of the user.
     * </p>
     *
     * @param emailId The email of the user
     * @return True if email is valid, false otherwise
     */
    public boolean validateEmailId(final String emailId) {
        return emailId.matches(EMAIL_PATTERN);
    }

    /**
     * <p>
     * Validates the password of the user.
     * </p>
     *
     * @param password The password of the user
     * @return True if password is valid, false otherwise
     */
    public boolean validatePassword(final String password) {
        return password.matches(PASSWORD_PATTERN);
    }
}
