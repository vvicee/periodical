package com.vvicee.service;

import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.Role;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;

import java.util.List;
import java.util.stream.Collectors;

import static com.vvicee.constant.servlet.MailConstant.ACTIVATION_CODE;
import static com.vvicee.constant.servlet.MailConstant.ACTIVATION_MESSAGE;

public class UserService {
    private final UserDAOImpl userDAO;
    private final List<User> users;
    private final MailService mailService;

    public UserService() throws DBException {
        mailService = new MailService();
        userDAO = new UserDAOImpl();
        users = userDAO.findAll().stream().filter(user -> !user.getRole().equals(Role.ADMIN)).collect(Collectors.toList());
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
        if (page <= 0) {
            page = 1;
        }
        int from = maxNumberUsers * (page - 1);
        int to = from + maxNumberUsers;


        if (to > users.size()) {
            to = users.size();
        }
        return users.subList(from, to);
    }

    public void sendActivationCode(User user) {
        String message = String.format(
                ACTIVATION_MESSAGE,
                user.getName(),
                user.getActivationCode()
        );

        mailService.sendMessage(user.getEmail(), ACTIVATION_CODE, message);
    }


    public boolean activateUser(String code) throws DBException {
        User user = userDAO.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);

        userDAO.update(user);
        return true;
    }

}
