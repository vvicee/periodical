package com.vvicee.service;

import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.PanelUI;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService service;


    @Before
    public void setUp() throws DBException {
        service = new UserService();
    }

    @Test
    public void getUsersTest(){
        assertEquals(service.getUsers(1), service.getUsers(0));
    }

    @Test
    public void activateUserTest() throws DBException {
        assertFalse(service.activateUser("+++"));
    }

    @Test
    public void changeBalanceTest() throws DBException {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.find(4);
        double balance = user.getBalance();
        service.changeBalance(user, 0);
        assertEquals(balance, user.getBalance(), 4);
    }
}
