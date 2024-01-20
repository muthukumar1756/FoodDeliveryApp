package com.swiggy.model;

import com.swiggy.customexception.HashAlgorithmNotFoundException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Provides hashed password for security purposes.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public class PasswordGenerator {

    private static PasswordGenerator passwordGenerator;

    private PasswordGenerator() {
    }

    public static PasswordGenerator getInstance() {
        if (null == passwordGenerator) {
            passwordGenerator = new PasswordGenerator();
        }

        return passwordGenerator;
    }

    public String hashPassword(final String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.substring(0, 25);
        } catch (NoSuchAlgorithmException message) {
            throw new HashAlgorithmNotFoundException(message.getMessage());
        }
    }
}


