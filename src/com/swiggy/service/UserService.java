package com.swiggy.service;

import com.swiggy.model.User;

public interface UserService {

    void setUser(final String userName, final String phoneNumber, final String emailId, final String password);

    boolean setUsers(final String userName, final String phoneNumber, final String emailId, final String password);

    boolean isUserExist(final String phoneNumber);

    void handleLoginValidation(final String phoneNumber, final String password);

    void setUserEmailId(User currentUser, String emailId);

    void setUserPhoneNumber(User currentUser, String phoneNumber);
}
