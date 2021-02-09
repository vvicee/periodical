package com.eresko.service;

import com.eresko.db.implDao.UserDAOImpl;
import com.eresko.entity.user.User;
import com.eresko.exception.DBException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserDAOImpl userDAO;
    private final List<User> users;

    public UserService() throws DBException {
        userDAO = new UserDAOImpl();
        users = userDAO.findAll();
    }

    public List<User> getActiveUsers() {
        return users.stream().filter(User::isActive).collect(Collectors.toList());
    }

    public List<User> getBlockedUsers() {
        return users.stream().filter(user -> !user.isActive()).collect(Collectors.toList());
    }

    public void changeBalance(User user, double money) throws DBException {
        double newBalance = user.getBalance() + money;
        user.setBalance(newBalance);
        userDAO.setBalance(user);
    }

}
