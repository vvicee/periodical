package com.eresko.db.implDao;

import com.eresko.db.DBManager;
import com.eresko.db.dao.EditionDAO;
import com.eresko.entity.edition.Edition;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.eresko.constant.db.query.EditionQueries.*;
import static com.eresko.constant.entity.EditionConstant.*;

public class EditionDAOImpl implements EditionDAO {

    DBManager dbManager = DBManager.getInstance();
    private final Logger logger = Logger.getLogger(EditionDAOImpl.class);

    @Override
    public List<Edition> findByTitle(String title) throws DBException {
        try {
            return getEditions(title, SQL_FIND_EDITION_BY_TITLE);
        } finally {
            logger.error("Failed find edition by title");
        }
    }

    @Override
    public List<Edition> findByTheme(String theme) throws DBException {
        try {
            return getEditions(theme, SQL_FIND_EDITION_BY_THEME);
        } finally {
            logger.error("Failed find edition by theme");
        }
    }

    public List<Edition> find(List<Integer> values) throws DBException {
        List<Edition> editions = new ArrayList<>();
        for (int i : values) {
            editions.add(find(i));
        }
        return editions;
    }

    @Override
    public Edition find(int id) throws DBException {
        Edition edition = null;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_FIND_EDITION_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                edition = parseEdition(resultSet);
            }

            dbManager.close(connection, statement, resultSet);
        } catch (SQLException exception) {
            logger.error("Failed find edition by id", exception);
            throw new DBException(exception);
        }

        return edition;
    }

    @Override
    public List<Edition> findAll() throws DBException {
        List<Edition> editions = new ArrayList<>();
        try {
            Connection connection = dbManager.getConnection();
            Statement statement = dbManager.getStatement(connection);
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_EDITIONS);

            while (resultSet.next()) {
                Edition edition = parseEdition(resultSet);
                editions.add(edition);
            }

            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception) {
            logger.error("Failed find all editions", exception);
            throw new DBException(exception);
        }
        return editions;
    }

    @Override
    public void add(Edition edition) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_ADD_EDITION);

            setParametersOfEdition(edition, statement);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                edition.setId(resultSet.getInt(EDITION_ID));
            }

            dbManager.commit(connection);
            dbManager.close(connection, statement, resultSet);
        } catch (SQLException exception) {
            logger.error("Failed add edition", exception);
            throw new DBException(exception);
        }

    }


    @Override
    public void delete(Edition edition) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_DELETE_EDITION);
            statement.setInt(1, edition.getId());
            statement.executeUpdate();

            dbManager.close(connection);
            dbManager.close(statement);
        } catch (SQLException exception) {
            logger.error("Failed delete edition", exception);
            throw new DBException(exception);
        }

    }

    @Override
    public void update(Edition edition) throws DBException {

        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_UPDATE_EDITION);
            setParametersOfEdition(edition, statement);
            statement.setInt(7, edition.getId());
            statement.executeUpdate();

            dbManager.commit(connection);
            dbManager.close(connection);
            dbManager.close(statement);

        } catch (SQLException exception) {
            logger.error("Failed update edition", exception);
            throw new DBException(exception);
        }


    }

    private void setParametersOfEdition(Edition edition, PreparedStatement statement) throws DBException {
        // title, publisher, description, price, category, theme
        try {
            statement.setString(1, edition.getTitle());
            statement.setString(2, edition.getPublisher());
            statement.setString(3, edition.getDescription());
            statement.setDouble(4, edition.getPrice());
            statement.setString(5, String.valueOf(edition.getCategory()).toLowerCase());
            statement.setString(6, String.valueOf(edition.getTheme()).toLowerCase());
        } catch (SQLException exception) {
            logger.error("Failed set parameters of edition", exception);
            throw new DBException(exception);
        }

    }

    private Edition parseEdition(ResultSet resultSet) throws DBException {
        Edition edition = new Edition();

        try {
            edition.setId(resultSet.getInt(EDITION_ID));
            edition.setTitle(resultSet.getString(EDITION_TITLE));
            edition.setPublisher(resultSet.getString(EDITION_PUBLISHER));
            edition.setDescription(resultSet.getString(EDITION_DESCRIPTION));
            edition.setTheme(resultSet.getString(EDITION_THEME));
            edition.setCategory(resultSet.getString(EDITION_CATEGORY));
            edition.setAvatarPath(resultSet.getString(EDITION_AVATAR));
            edition.setPrice(resultSet.getDouble(EDITION_PRICE));
        } catch (SQLException exception) {
            logger.error("Failed parse edition", exception);
            throw new DBException(exception);
        }


        return edition;
    }

    private List<Edition> getEditions(String req, String sqlFindEdition) throws DBException {
        List<Edition> editions = new ArrayList<>();

        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, sqlFindEdition);
            statement.setString(1, req);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Edition edition = parseEdition(resultSet);
                editions.add(edition);
            }
        } catch (SQLException | DBException exception) {
            logger.error("Failed get editions", exception);
            throw new DBException(exception);
        }


        return editions;
    }

}
