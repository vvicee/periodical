package com.eresko.db.dao;

import com.eresko.entity.subscription.Subscription;
import com.eresko.exception.DBException;

import java.util.List;

public interface SubscriptionDAO extends IDAO<Subscription> {
    List<Subscription> findByUserId(int id) throws DBException;

    List<Integer> findEditionIdFromSubscriptionOfUser(int userId) throws DBException;
}
