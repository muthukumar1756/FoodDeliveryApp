package com.swiggy.model;

import com.swiggy.exception.HashAlgorithmNotFoundException;

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

    /**
     * <p>
     * Gets the password generator class object.
     * </p>
     *
     * @return The password generator object
     */
    public static PasswordGenerator getInstance() {
        if (null == passwordGenerator) {
            passwordGenerator = new PasswordGenerator();
        }

        return passwordGenerator;
    }

    /**
     * <p>
     * Hashes the user password.
     * </p>
     *
     * @param password password of the current user
     * @return The hashed password
     */
    public String hashPassword(final String password) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] encodedHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            final StringBuilder hexString = new StringBuilder();
            for (final byte hashByte : encodedHash) {
                hexString.append(String.format("%02x", hashByte));
            }

            return hexString.substring(0, 25);
        } catch (NoSuchAlgorithmException message) {
            throw new HashAlgorithmNotFoundException(message.getMessage());
        }
    }
}