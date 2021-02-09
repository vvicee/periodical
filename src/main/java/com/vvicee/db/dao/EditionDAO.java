package com.vvicee.db.dao;

import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;

import java.util.List;

public interface EditionDAO extends IDAO<Edition> {
    List<Edition> findByTitle(String title) throws DBException;

    List<Edition> findByTheme(String theme) throws DBException;
}
