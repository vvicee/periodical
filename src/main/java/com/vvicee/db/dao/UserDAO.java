package com.vvicee.db.dao;

import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;


public interface UserDAO extends IDAO<User> {
    User isExist(String email, String password) throws DBException;

    User findByEmail(String email) throws DBException;

    void setBalance(User user) throws DBException;
}
