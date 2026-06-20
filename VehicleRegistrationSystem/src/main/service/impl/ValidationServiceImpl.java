package main.service.impl;

import main.service.ValidationService;

/**
 * Implementation of the {@link ValidationService} interface.
 * Provides concrete validation logic for user input fields such as username,
 * password, age, and phone number. Each method prints appropriate error messages
 * when validation fails and returns {@code false}. Returns {@code true} only if
 * all validation criteria are met.
 */
public class ValidationServiceImpl implements ValidationService {

    /**
     * Validates a username according to the following rules:
     * <ul>
     *   <li>Must not be null or empty.</li>
     *   <li>Length must be between 4 and 20 characters inclusive.</li>
     *   <li>May only contain letters (a-z, A-Z) and digits (0-9). No special characters or spaces.</li>
     * </ul>
     *
     * @param username the username string to validate
     * @return {@code true} if the username is valid, otherwise {@code false}
     */
    @Override
    public boolean validateUserName(String username) {
        // Check for null or empty input
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }

        // Check length constraints
        if (username.length() < 4 || username.length() > 20) {
            System.out.println("Username must be between 4 and 20 characters.");
            return false;
        }

        // Ensure only alphanumeric characters using regex
        if (!username.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("Username can contain only letters and numbers.");
            return false;
        }

        // All checks passed
        return true;
    }

    /**
     * Validates a password according to the following security rules:
     * <ul>
     *   <li>Must not be null or empty.</li>
     *   <li>Minimum length of 8 characters.</li>
     *   <li>Must contain at least one uppercase letter (A-Z).</li>
     *   <li>Must contain at least one lowercase letter (a-z).</li>
     *   <li>Must contain at least one digit (0-9).</li>
     *   <li>Must contain at least one special character from the set {@code @#$%^&+=!}.</li>
     * </ul>
     *
     * @param password the password string to validate
     * @return {@code true} if the password meets all criteria, otherwise {@code false}
     */
    @Override
    public boolean validatePassword(String password) {
        // Check for null or empty input
        if (password == null || password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            return false;
        }

        // Minimum length requirement
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters.");
            return false;
        }

        // Check for at least one uppercase letter using regex
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        // Check for at least one digit
        if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one number.");
            return false;
        }

        // Check for at least one special character from the defined set
        if (!password.matches(".*[@#$%^&+=!].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }

        // All checks passed
        return true;
    }

    /**
     * Validates an age value (as a float) according to the following rules:
     * <ul>
     *   <li>Must be greater than zero.</li>
     *   <li>Must be at least 18 (adult).</li>
     *   <li>Must not exceed 120 (reasonable maximum).</li>
     * </ul>
     *
     * @param age the age value to validate
     * @return {@code true} if the age is within the allowed range, otherwise {@code false}
     */
    @Override
    public boolean validateAge(float age) {
        // Age must be positive
        if (age <= 0) {
            System.out.println("Invalid age!");
            return false;
        }

        // Minimum age requirement
        if (age < 18) {
            System.out.println("Age must be greater than 18.");
            return false;
        }

        // Maximum age limit
        if (age > 120) {
            System.out.println("Age must not exceed 120.");
            return false;
        }

        // All checks passed
        return true;
    }

    /**
     * Validates an Egyptian phone number according to the following rules:
     * <ul>
     *   <li>Must not be null or empty.</li>
     *   <li>Must match the pattern for Egyptian mobile numbers:
     *       starts with {@code 01} followed by one of {@code 0,1,2,5} and then exactly 8 digits.</li>
     * </ul>
     * <p>
     * The regex {@code ^01[0125]\\d{8}$} breaks down as:
     * <ul>
     *   <li>{@code ^} – start of string</li>
     *   <li>{@code 01} – literal "01"</li>
     *   <li>{@code [0125]} – one of the digits 0,1,2,5 (for providers: Vodafone, Etisalat, Orange, We)</li>
     *   <li>{@code \\d{8}} – exactly 8 digits</li>
     *   <li>{@code $} – end of string</li>
     * </ul>
     *
     * @param phoneNumber the phone number string to validate
     * @return {@code true} if the phone number matches the Egyptian pattern, otherwise {@code false}
     */
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        // Check for null or empty input
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return false;
        }

        // Validate against Egyptian mobile number pattern
        if (!phoneNumber.matches("^01[0125]\\d{8}$")) {
            System.out.println("Invalid Egyptian phone number.");
            return false;
        }

        // All checks passed
        return true;
    }
}