package com.eresko.db.dao;

import com.eresko.exception.DBException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    T find(int id) throws DBException;

    List<T> findAll() throws  DBException;

    void add(T element) throws DBException;

    void delete(T element) throws DBException;

    void update(T element) throws DBException;
}
