package com.vvicee.service;

import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.Role;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserDAOImpl userDAO;
    private final List<User> users;

    public UserService() throws DBException {
        userDAO = new UserDAOImpl();
        users = userDAO.findAll().stream().filter(user -> !user.getRole().equals(Role.ADMIN)).collect(Collectors.toList());
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

    public int getMaxNumberPage() {
        int usersNumber = users.size();
        return (int) Math.ceil((double) usersNumber / 6);
    }

    public List<User> getUsers(int page) {
        int maxNumberUsers = 6;
        int from = maxNumberUsers * (page - 1);
        int to = from + maxNumberUsers;

        if (to > users.size()) {
            to = users.size();
        }
        return users.subList(from, to);
    }

}
