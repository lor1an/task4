/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.connectionDB.DBpool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import ua.kpi.exceptions.DAOException;
import java.sql.DriverManager;

/**
 *
 * @author lor1an
 */
public class BasicCRUD {

    protected Connection connect;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet result;
    protected Logger logger;

    public BasicCRUD(Logger log) {
        logger = log;
    }
    /*
     * Tries to get connection from tomcat connection pool
     * @throws SQLException
     */

    public void makeConnection() {
        try {
            connect = DBpool.getInstance().getConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
    }

    /*
     * Takes preparedStatement and inserts args in it.
     * @throws SQLException
     */
    public void insertValues(Object... args) {
        for (int i = 0; i < args.length; i++) {
            try {
                preparedStatement.setObject(i + 1, args[i]);
            } catch (SQLException ex) {
                logger.info("Sql exception thrown;");
                logger.error("Exception thrown!", ex);
            }
        }

    }
    /*
     * Takes parameter String query, makes it prepared statement and pefroms 
     * query as specified. Used for direct work with a database, for queries like SELECT
     * @throws DAOException
     * @throws SQLException
     */

    public ResultSet getItems(String query, Object... args) {
        try {
            preparedStatement = connect.prepareStatement(query);
            insertValues(args);
            result = preparedStatement.executeQuery();
        } catch (Exception ex) {
            logger.info("Exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return result;
    }

    /*
     * Takes parameter string query, makes it prepared statement and perfoms query.
     * Used for direct work with a database, for queries like UPDATE,INSERT,DELETE
     */
    public int executeQuery(String query, Object... args) {
        int resultValue = -1;
        try {
            preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertValues(args);
            resultValue = preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return resultValue;
    }
    /*
     * Closes connections, deletes prepared statement.
     * Used when work is finished
     */

    public void closeConnection() {
        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
    }
}
