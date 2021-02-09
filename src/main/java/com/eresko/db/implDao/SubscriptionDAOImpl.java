package com.eresko.db.implDao;

import com.eresko.db.DBManager;
import com.eresko.db.dao.SubscriptionDAO;
import com.eresko.entity.subscription.Month;
import com.eresko.entity.subscription.Subscription;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.eresko.constant.db.query.SubscriptionQueries.*;
import static com.eresko.constant.entity.SubscriptionConstant.*;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    private final DBManager dbManager = DBManager.getInstance();
    private final Logger logger = Logger.getLogger(SubscriptionDAOImpl.class);

    @Override
    public List<Integer> findEditionIdFromSubscriptionOfUser(int userId) throws DBException {
        List<Integer> editionId = new ArrayList<>();

        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_SELECT_ALL_EDITION_ID_BY_USER_SUBS);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                editionId.add(resultSet.getInt(EDITION_ID_FROM_SUBSCRIPTION));
            }

            dbManager.close(connection, statement, resultSet);
        } catch (SQLException exception){
            logger.error("Failed find edition id from subscription of user", exception);
            throw new DBException(exception);
        }

        return editionId;
    }

    @Override
    public List<Subscription> findByUserId(int id) throws DBException {
        List<Subscription> subscriptions = new ArrayList<>();

        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_FIND_SUBS_BY_USER_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                subscriptions.add(parseSubscription(resultSet));
            }

            dbManager.close(connection, statement, resultSet);
        } catch (SQLException exception){
            logger.error("Failed find subscription by user id", exception);
            throw new DBException(exception);
        }

        return subscriptions;
    }


    @Override
    public Subscription find(int id){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Subscription> findAll(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Subscription subscription) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_ADD_SUBSCRIPTION);

            setParametersOfSubscription(subscription, statement, connection);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                subscription.setId(resultSet.getInt(SUBSCRIPTION_ID));
            }

            dbManager.commit(connection);
            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception){
            logger.error("Failed add subscription", exception);
            throw new DBException(exception);
        }

    }

    @Override
    public void delete(Subscription element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Subscription element) {
        throw new UnsupportedOperationException();
    }

    private void setParametersOfSubscription(Subscription subscription, PreparedStatement statement, Connection connection) throws DBException {
        try {
            statement.setInt(1, subscription.getEditionId());
            statement.setInt(2, subscription.getUserId());
            statement.setInt(3, subscription.getYear());
            Array months = connection.createArrayOf("text", subscription.getMonths().toArray());
            statement.setArray(4, months);
        } catch (SQLException exception) {
            logger.error("Failed set parameters of subscription", exception);
            throw new DBException(exception);
        }
    }

    private Subscription parseSubscription(ResultSet resultSet) throws DBException {
        Subscription subscription = new Subscription();

        try{
            subscription.setId(resultSet.getInt(SUBSCRIPTION_ID));
            subscription.setEditionId(resultSet.getInt(EDITION_ID_FROM_SUBSCRIPTION));
            subscription.setUserId(resultSet.getInt(USER_ID_FROM_SUBSCRIPTION));
            subscription.setYear(resultSet.getInt(SUBSCRIPTION_YEAR));
            List<Month> months = getMonthsFromDB(resultSet);
            subscription.setMonths(months);
        } catch (SQLException exception){
            logger.error("Failed parse subscription", exception);
            throw new DBException(exception);
        }

        return subscription;
    }

    private List<Month> getMonthsFromDB(ResultSet resultSet) throws DBException {
        List<Month> months = new ArrayList<>();

        try {
            Array m = resultSet.getArray(SUBSCRIPTION_MONTHS);
            String[] nameOfMonths = (String[]) m.getArray();
            for (String el : nameOfMonths) {
                months.add(Month.valueOf(el));
            }
        } catch (SQLException exception){
            logger.error("Failed get months from db", exception);
            throw new DBException(exception);
        }

        return months;
    }
}
