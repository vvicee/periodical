package com.vvicee.db.dao;

import com.vvicee.entity.subscription.Subscription;
import com.vvicee.exception.DBException;

import java.util.List;

public interface SubscriptionDAO extends IDAO<Subscription> {
    List<Subscription> findByUserId(int id) throws DBException;

    List<Integer> findEditionIdFromSubscriptionOfUser(int userId) throws DBException;
}
