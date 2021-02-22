package com.vvicee.db;

import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    private static UserDAOImpl userDAO;
    private static User user;

    @BeforeClass
    public static void setUp() throws DBException {
        userDAO = new UserDAOImpl();
        user = initUser();
        userDAO.add(user);
    }

    private static User initUser() {
        return new User.Builder()
                .setId(200000)
                .setName("Test")
                .setSurname("Testik")
                .setEmail("test@mail.ru")
                .setPassword("test123")
                .setAvatarPath(null)
                .setBalance(12345)
                .setRole("user")
                .setActive(true)
                .setActivationCode("12345")
                .setMailings(true)
                .build();
    }

    @Test
    public void findByActivationCodeTest() throws DBException {
        User actual = userDAO.findByActivationCode("12345");
        assertEquals(actual, user);
    }

    @Test
    public void setBalanceTest() throws DBException {
        User actual = userDAO.findByActivationCode("12345");
        actual.setBalance(120000);
        userDAO.setBalance(actual);

        assertNotEquals(actual, user);
    }

    @Test
    public void findByEmailTest() throws DBException {
        User actual = userDAO.findByEmail("test@mail.ru");
        assertEquals(actual, user);
    }


    @Test
    public void testFindUserByLoginAndPassword() throws DBException {
        User actual = userDAO.isExist("test@mail.ru", "12345");
        assertEquals(actual, user);
    }

    @Test
    public void testFindById() throws DBException {
        User actual = userDAO.find(200000);
        assertNotNull(actual);
    }

    @Test
    public void testFindAll() throws DBException {
        List<User> actual = userDAO.findAll();
        assertNotNull(actual);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteInvalidUser() {
        try {
            userDAO.delete(null);
        } catch (DBException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddInvalidUser() {
        User actual = initUser();
        try {
            userDAO.add(actual);
        } catch (DBException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void updateTest() throws DBException {
        User actual = userDAO.findByActivationCode("12345");
        actual.setName("neTest");
        String name = actual.getName();
        userDAO.update(actual);
        String newName = actual.getName();

        assertNotEquals(name, newName);
    }

    @AfterClass
    public static void deleteUserTest() throws DBException {
        userDAO.delete(user);
    }
}
