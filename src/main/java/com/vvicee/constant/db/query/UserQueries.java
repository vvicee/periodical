package com.vvicee.constant.db.query;

public class UserQueries {

    //language=SQL
    public static final String SQL_FIND_ALL_USERS = "SELECT * FROM  users";
    //language=SQL
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    //language=SQL
    public static final String SQL_ADD_USER = "INSERT INTO users (name, surname, email, password, mailings, active, code ) VALUES (?,?,?,?,?,?,?)";
    //language=SQL
    public static final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? and password=?";
    //language=SQL
    public static final String SQL_UPDATE_USER = "UPDATE users SET name=?, surname=?, email=?, password=?, mailings=?, active=?, code=? WHERE user_id=?";
    //language=SQL
    public static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id=?";
    //language=SQL
    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    //language=SQL
    public static final String SQL_SET_BALANCE = "UPDATE users SET balance=? WHERE user_id =?";
    //language=SQL
    public static final String SQL_FIND_USER_BY_ACTIVATION_CODE = "SELECT * FROM users WHERE code =?";
}
