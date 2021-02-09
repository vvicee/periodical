package com.eresko.db.implDao;

import com.eresko.db.DBManager;
import com.eresko.db.dao.UserDAO;
import com.eresko.entity.user.User;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.eresko.constant.db.query.UserQueries.*;
import static com.eresko.constant.entity.UserConstant.*;

public class UserDAOImpl implements UserDAO {

    private final DBManager dbManager = DBManager.getInstance();
    private final Logger logger = Logger.getLogger(UserDAOImpl.class);


    @Override
    public void setBalance(User user) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_SET_BALANCE);

            statement.setDouble(1, user.getBalance());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

            dbManager.commit(connection);
            dbManager.close(connection);
            dbManager.close(statement);

        } catch (SQLException exception) {
            logger.error("Failed set balance ", exception);
            throw new DBException(exception);
        }

    }

    @Override
    public User findByEmail(String email) throws DBException {
        User user = null;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = parseUser(resultSet);
            }

            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception) {
            logger.error("Failed find user by email", exception);
            throw new DBException(exception);
        }

        return user;
    }

    @Override
    public boolean isExist(String email, String password) throws DBException {
        User user = null;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_FIND_USER_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = parseUser(resultSet);
            }

            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception) {
            logger.error("Failed find user by email and password", exception);
            throw new DBException(exception);
        }

        return user != null;
    }

    @Override
    public User find(int id) throws DBException {
        User user = new User();

        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_FIND_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = parseUser(resultSet);
            }

            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception) {
            logger.error("Failed find user by id", exception);
            throw new DBException(exception);
        }

        return user;
    }

    @Override
    public List<User> findAll() throws DBException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = dbManager.getConnection();
            Statement statement = dbManager.getStatement(connection);
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);

            while (resultSet.next()) {
                User user = parseUser(resultSet);
                users.add(user);
            }

            dbManager.close(connection, statement, resultSet);
        } catch (SQLException exception) {
            logger.error("Failed find all users", exception);
            throw new DBException(exception);
        }

        return users;
    }

    @Override
    public void add(User user) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_ADD_USER);

            setParametersOfUser(user, statement);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(USER_ID));
            }

            dbManager.commit(connection);
            dbManager.close(connection, statement, resultSet);

        } catch (SQLException exception) {
            logger.error("Failed add user", exception);
            throw new DBException(exception);
        }

    }

    @Override
    public void delete(User user) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_DELETE_USER);
            statement.setInt(1, user.getId());
            statement.executeUpdate();

            dbManager.close(connection);
            dbManager.close(statement);
        } catch (SQLException exception) {
            logger.error("Failed delete user", exception);
            throw new DBException(exception);
        }

    }

    @Override
    public void update(User user) throws DBException {
        try {
            Connection connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = dbManager.getPreparedStatement(connection, SQL_UPDATE_USER);

            setParametersOfUser(user, statement);
            statement.setInt(8, user.getId());
            statement.executeUpdate();

            dbManager.commit(connection);
            dbManager.close(connection);
            dbManager.close(statement);
        } catch (SQLException exception) {
            logger.error("Failed update user", exception);
            throw new DBException(exception);
        }

    }

    private void setParametersOfUser(User user, PreparedStatement statement) throws DBException {
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isMailings());
            statement.setBoolean(6, user.isActive());
            statement.setString(7, String.valueOf(user.getRole()).toLowerCase());

        } catch (SQLException exception) {
            logger.error("Failed set parameters of user", exception);
            throw new DBException(exception);
        }
    }

    private User parseUser(ResultSet resultSet) throws DBException {
        User user = new User();
        try {
            user.setId(resultSet.getInt(USER_ID));
            user.setName(resultSet.getString(USER_NAME));
            user.setSurname(resultSet.getString(USER_SURNAME));
            user.setEmail(resultSet.getString(USER_EMAIL));
            user.setPassword(resultSet.getString(USER_PASSWORD));
            user.setAvatarPath(resultSet.getString(USER_AVATAR));
            user.setBalance(resultSet.getDouble(USER_BALANCE));
            user.setRole(resultSet.getString(USER_ROLE));
            user.setActive(resultSet.getBoolean(USER_ACTIVE));
        } catch (SQLException exception) {
            logger.error("Failed parse user", exception);
            throw new DBException(exception);
        }
        return user;

    }

}
