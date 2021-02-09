package com.vvicee.service;

import com.vvicee.db.dao.SubscriptionDAO;
import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.db.implDao.SubscriptionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.entity.subscription.Month;
import com.vvicee.entity.subscription.Subscription;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    private final Logger logger = Logger.getLogger(SubscriptionService.class);

    SubscriptionDAO subscriptionDAO;
    EditionDAOImpl editionDAO;

    public SubscriptionService() {
        subscriptionDAO = new SubscriptionDAOImpl();
        editionDAO = new EditionDAOImpl();
    }

    public List<Edition> getEditions(User user) throws DBException {
        List<Integer> idOfEditions = subscriptionDAO.findEditionIdFromSubscriptionOfUser(user.getId());
        return editionDAO.find(idOfEditions);
    }

    public List<Subscription> getSubscriptions(int id) throws DBException {
        return subscriptionDAO.findByUserId(id);
    }

    public boolean createSubscription(User user, int editionId, String[] months) throws DBException {

        Subscription subscription = new Subscription();
        subscription.setUserId(user.getId());
        subscription.setEditionId(editionId);
        subscription.setYear(LocalDate.now().getYear());
        subscription.setMonths(Month.convertArrayToList(months));
        logger.debug("Adding subscription " + subscription);

        if (withdrawMoneyForSubscription(user, editionId, months)) {
            subscriptionDAO.add(subscription);
            logger.debug(" Subscription added successfully");

            return true;
        }
        return false;
    }

    private boolean withdrawMoneyForSubscription(User user, int editionId, String[] months) throws DBException {
        UserService userService = new UserService();
        Edition edition = editionDAO.find(editionId);
        double cost = edition.getPrice() * months.length;
        if (user.getBalance() >= cost) {
            userService.changeBalance(user, -cost);
            return true;
        }
        logger.debug("Not enough money to subscribe ");
        return false;
    }

}