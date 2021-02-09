package com.eresko.db.dao;

import com.eresko.entity.edition.Edition;
import com.eresko.exception.DBException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EditionDAO extends IDAO<Edition> {
    List<Edition> findByTitle(String title) throws DBException;

    List<Edition> findByTheme(String theme) throws DBException;
}
