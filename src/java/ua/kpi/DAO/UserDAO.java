/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.DAO.Interfaces.UserCRUD;
import ua.kpi.exceptions.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.kpi.model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class UserDAO extends BasicCRUD implements UserCRUD {

    private static final String ADD_USER = "insert into users(Name, Surname,Login, Password, IsLibrarian) values (?,?,?,?,?);";
    private static final String FIND_USER_BY_LOGIN = "select Id,Name,Surname,Password, IsLibrarian from users where Login = ?;";
    private static final String UPDATE_USER = "update users set Name=?, Surname=?,Login=?, Password=?, IsLibrarian=? where ID=?;";
    private static final String FIND_USER_BY_ID = "select Name,Surname,Login,Password,IsLibrarian from users where ID = ?;";
    private static final String DELETE_USER = "delete from users where ID=?;";
    private static final String GET_ALL_USERS = "select ID,Name,Surname,Login,Password,IsLibrarian from users;";

    public UserDAO(Logger logger) {
        super(logger);
    }

    @Override
    public User findById(int id) throws DAOException {
        User user = null;
        try {
            super.makeConnection();
            Object[] args = {id};
            result = super.getItems(FIND_USER_BY_ID, args);
            if (result.next()) {
                user = new User();
                user.setId(id);
                user.setName(result.getString("Name"));
                user.setSurname(result.getString("Surname"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return user;
    }

    @Override
    public boolean update(User entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName(), entity.getSurname(), entity.getLogin(),
            entity.getPassword(), entity.isIsLibrarian(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_USER, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int save(User entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName(), entity.getSurname(), entity.getLogin(),
            entity.getPassword(), entity.isIsLibrarian()};
        resultValue = super.executeQuery(ADD_USER, args);
        super.closeConnection();
        if (resultValue > 0) {
            return resultValue;
        } else {
            return 0;
        }
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getId()};
        resultValue = super.executeQuery(DELETE_USER, args);
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList();
        try {
            super.makeConnection();
            Object[] args = {};
            result = super.getItems(GET_ALL_USERS, args);
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("ID"));
                user.setName(result.getString("Name"));
                user.setSurname(result.getString("Surname"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                users.add(user);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) throws DAOException {

        User user = null;
        try {
            super.makeConnection();
            Object[] args = {login};
            result = super.getItems(FIND_USER_BY_LOGIN, args);
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("Id"));
                user.setName(result.getString("Name"));
                user.setSurname(result.getString("Surname"));
                user.setLogin(login);
                user.setPassword(result.getString("Password"));
                if (result.getInt("IsLibrarian") == 1) {
                    user.setIsLibrarian(true);
                } else {
                    user.setIsLibrarian(false);
                }
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!", ex);
        }
        return user;

    }
}
