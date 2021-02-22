package com.vvicee.db;

import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.db.implDao.SubscriptionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.entity.subscription.Subscription;
import com.vvicee.exception.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SubscriptionDaoTest {

    private Subscription subsTest;
    private SubscriptionDAOImpl dao;
    private Edition edition;

    @Before
    public void setUp() throws Exception {
        subsTest = new Subscription();
        dao = new SubscriptionDAOImpl();
        EditionDAOImpl editionDAO = new EditionDAOImpl();
        edition = editionDAO.find(4);
        subsTest = initSubs();
        dao.add(subsTest);
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(subsTest);
        subsTest = null;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateTest() {
        dao.update(subsTest);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findTest() {
        dao.find(1);
    }

    @Test
    public void findEditionIdTest() throws DBException {
        assertNotEquals(Collections.singletonList(edition.getId()), dao.findEditionIdFromSubscriptionOfUser(4));
    }

    private Subscription initSubs() {
        Subscription subscription = new Subscription();
        subscription.setEdition(edition);
        subscription.setUserId(4);
        subscription.setYear(2021);
        subscription.setMonths(new ArrayList<>());
        return subscription;
    }

}
