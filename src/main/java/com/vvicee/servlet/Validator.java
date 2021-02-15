package com.vvicee.servlet;

import static com.vvicee.constant.servlet.ErrorsConstant.*;

public class Validator {

    public static boolean validateName(String name, String surname) {

        return name.trim().matches(REGEX_NAME) && surname.trim().matches(REGEX_NAME);
    }

    public static boolean validatePassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

    public static boolean validateEmail(String email) {
        return email.matches(REGEX_EMAIL);
    }

    /**
     * @param password - password from database
     * @param input    - password that input user
     * @return true if equals
     */
    public static boolean comparePasswords(String password, String input) {
        return password.equals(input);
    }

    public static boolean validateName(String name) {
        return name.matches(REGEX_NAME);
    }

    public static boolean validatePrice(double price) {
        return price > 0;
    }
}
