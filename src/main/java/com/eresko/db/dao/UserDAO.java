package com.eresko.db.dao;

import com.eresko.entity.user.User;
import com.eresko.exception.DBException;


public interface UserDAO extends IDAO<User> {
    boolean isExist(String email, String password) throws DBException;

    User findByEmail(String email) throws DBException;

    void setBalance(User user) throws DBException;
}
