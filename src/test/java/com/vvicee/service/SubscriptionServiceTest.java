package com.vvicee.service;

import com.vvicee.db.dao.SubscriptionDAO;
import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.db.implDao.SubscriptionDAOImpl;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SubscriptionServiceTest {
    private final SubscriptionService service = new SubscriptionService();
    private final SubscriptionDAO dao = new SubscriptionDAOImpl();
    private User testUser;
    private Edition editionTest;

    @Before
    public void setUp() throws DBException {
        UserDAOImpl userDAO = new UserDAOImpl();
        testUser = userDAO.find(4);
        EditionDAOImpl editionDAO = new EditionDAOImpl();
        editionTest = editionDAO.find(20);
    }

    @Test
    public void getSubscriptionsTest() throws DBException {
        assertEquals(service.getSubscriptions(testUser.getId()), dao.findByUserId(testUser.getId()));
    }

    @Test
    public void withdrawMoneyForSubscriptionTest() throws DBException {
        assertFalse(service.withdrawMoneyForSubscription(testUser, 10000000, new String[]{}));
    }

    @Test(expected = NullPointerException.class)
    public void createSubscriptionTest() throws DBException {
        assertFalse(service.createSubscription(testUser, 10000000, new String[]{}));
    }

    @Test
    public void editionInSubscriptionTest() throws DBException {
        assertFalse(service.editionInSubscription(null));
        assertFalse(service.editionInSubscription(editionTest));
    }

}
