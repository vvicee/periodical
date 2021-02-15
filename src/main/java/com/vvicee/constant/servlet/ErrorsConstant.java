package com.vvicee.constant.servlet;

public class ErrorsConstant {
    public static final String ERRORS = "errors";
    public static final String UNREGISTERED_USER_ERROR = "Unregistered user!";
    public static final String INCORRECT_PASSWORD_ERROR = "Incorrect password!";
    public static final String INCORRECT_NAME_ERROR = "Incorrect name!";
    public static final String REGEX_NAME = "[а-яА-ЯёЁa-zA-Z]+";
    public static final String REGEX_PASSWORD = ".{4,}";
    public static final String INCORRECT_PASSWORD_LENGTH_ERROR = "Password cannot be shorted than 5 characters";
    public static final String REGEX_EMAIL = "[a-zA-Z0-9]+@[a-z]+\\.[a-z]+";
    public static final String INCORRECT_EMAIL_ERROR = "Incorrect email!";
    public static final String USER_ALREADY_EXISTS_ERROR = "User with this email already exists!";
    public static final String INCORRECT_TITLE_ERROR = "Incorrect title!";
    public static final String INCORRECT_PUBLISHER_ERROR = "Incorrect publisher!";
    public static final String INCORRECT_DESCRIPTION_ERROR = "Incorrect description!";
    public static final String INCORRECT_PRICE_ERROR = "Incorrect price!";
    public static final String EDITION_ALREADY_EXISTS_ERROR = "Edition already exists!";
    public static final String EDITION_IN_SUBSCRIPTION_ERROR = "Unable to delete an edition" +
            "while it is in active subscription";
    public static final String NOT_ACTIVATED_ERROR = "Account is not activated! Check your mail.";
}
