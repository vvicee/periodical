package com.eresko.db;

import com.eresko.exception.DBException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static com.eresko.constant.db.DBConfigConstant.*;

public class DBManager {

    private final Logger logger = Logger.getLogger(DBManager.class);
    private final Properties daoConfig = new Properties();
    private static DBManager dbManager;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }

        return dbManager;
    }

    /**
     * create connection to database
     *
     * @return connection
     */
    public Connection getConnection() throws DBException {

        try {
            String url = getDaoConfig().getProperty(DB_URL);
            String user = getDaoConfig().getProperty(DB_USER);
            String password = getDaoConfig().getProperty(DB_PASSWORD);
            Class.forName(getDaoConfig().getProperty(DB_DRIVER));
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException exception) {
            logger.error("Failed get connection", exception);
            throw new DBException(exception);
        }

    }

    /**
     * create {@link Statement}
     *
     * @param connection - connection to database
     * @return statement created from connection
     */
    public Statement getStatement(Connection connection) throws DBException {
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException exception) {
            logger.error("Failed get statement", exception);
            throw new DBException(exception);
        }
        return statement;
    }

    /**
     * create {@link PreparedStatement}
     *
     * @param connection      - connection to database
     * @param sqlQuery - sql query
     * @return prepared statement for sql query
     */
    public PreparedStatement getPreparedStatement(Connection connection, String sqlQuery) throws DBException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            logger.error("Failed get preparedStatement", exception);
            throw new DBException(exception);
        }
        return statement;
    }

    private Properties getDaoConfig() throws DBException {
        if (daoConfig.isEmpty()) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
                daoConfig.load(inputStream);
            } catch (IOException exception) {
                logger.error("Failed get daoConfig", exception);
                throw new DBException(exception);
            }
        }
        return daoConfig;
    }


    /**
     * closes a {@link Connection}
     *
     * @param connection - connection that need to close
     */
    public void close(Connection connection) throws DBException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Failed close connection", e);
                throw new DBException(e);
            }
        }
    }

    /**
     * closes a {@link Statement}
     *
     * @param statement - statement that need to close
     */
    public void close(Statement statement) throws DBException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Failed close statement", e);
                throw new DBException(e);
            }
        }
    }

    /**
     * closes a {@link ResultSet}
     *
     * @param resultSet - result set that need to close
     */
    public void close(ResultSet resultSet) throws DBException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Failed close resultSet", e);
                throw new DBException(e);
            }
        }
    }

    /**
     * closes resources.
     *
     * @param connection - connection that need to close
     * @param statement  - statement that need to close
     * @param resultSet  - result set that need to close
     */
    public void close(Connection connection, Statement statement, ResultSet resultSet) throws DBException {
        close(resultSet);
        close(statement);
        close(connection);
    }

    /**
     * @param con - connection where execute commit
     */
    public void commit(Connection con) throws DBException {
        try {
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            logger.error("Failed commit", e);
            throw new DBException(e);
        }
    }

    /**
     * @param connection - connection where execute rollback
     */
    private void rollback(Connection connection) throws DBException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.warn("Rollback produced", e);
            throw new DBException(e);
        }
    }
}
