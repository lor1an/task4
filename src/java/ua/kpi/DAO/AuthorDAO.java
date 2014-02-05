/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.DAO.Interfaces.AuthorCRUD;
import ua.kpi.exceptions.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.kpi.model.Author;
import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class AuthorDAO extends BasicCRUD implements AuthorCRUD {

    private static final String FIND_AUTHOR_BY_ID = "select Name,Surname from authors where ID = ?;";
    private static final String FIND_AUTHORS_BY_NAME = "select ID,Surname from authors where Name = ?;";
    private static final String FIND_AUTHORS_BY_SURNAME = "select ID,Name from authors where Surname = ?;";
    private static final String FIND_AUTHORS_BY_ALL = "select ID from authors where Name = ? and Surname = ?;";
    private static final String UPDATE_AUTHOR = "update authors set Name = ?, Surname=? where ID=?;";
    private static final String ADD_AUTHOR = "insert into authors(Name, Surname) values (?,?);";
    private static final String GET_ALL_AUTHORS = "select id, name. surname from authors;";
    private static final String DELETE_AUTHOR = "delete from authors where id=?";

    AuthorDAO(Logger logger) {
        super(logger);
    }

    @Override
    public Author findById(int id) throws DAOException {
        Author author = null;
        try {
            super.makeConnection();
            Object[] args = {id};
            result = super.getItems(FIND_AUTHOR_BY_ID, args);
            if (result.next()) {
                author = new Author();
                author.setId(id);
                author.setName(result.getString("Name"));
                author.setSurname(result.getString("Surname"));
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return author;
    }

    @Override
    public boolean update(Author entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName(), entity.getSurname(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_AUTHOR, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int save(Author entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName(), entity.getSurname()};
        resultValue = super.executeQuery(ADD_AUTHOR, args);
        super.closeConnection();
        if (resultValue > 0) {
            return resultValue;
        } else {
            return 0;
        }
    }

    @Override
    public boolean delete(Author entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getId()};
        resultValue = super.executeQuery(DELETE_AUTHOR, args);
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Author> getAll() throws DAOException {
        List<Author> authors = new ArrayList();
        try {
            super.makeConnection();
            Object[] args = {};
            result = super.getItems(GET_ALL_AUTHORS, args);
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("ID"));
                author.setName(result.getString("Name"));
                author.setSurname(result.getString("Surname"));
                authors.add(author);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return authors;
    }

    @Override
    public List<Author> findByName(String name) throws DAOException {
        List<Author> authors = new ArrayList();
        try {
            super.makeConnection();
            Object[] args = {name};
            result = super.getItems(FIND_AUTHORS_BY_NAME, args);
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("ID"));
                author.setName(name);
                author.setSurname(result.getString("Surname"));
                authors.add(author);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return authors;
    }

    @Override
    public List<Author> findBySurname(String surname) throws DAOException {
        List<Author> authors = new ArrayList();
        try {
            super.makeConnection();
            Object[] args = {surname};
            result = super.getItems(FIND_AUTHORS_BY_SURNAME, args);
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("ID"));
                author.setName(surname);
                author.setSurname(result.getString("Name"));
                authors.add(author);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return authors;
    }

    @Override
    public List<Author> findByAll(String name, String surname) throws DAOException {
        List<Author> authors = new ArrayList();
        try {
            super.makeConnection();
            Object[] args = {name, surname};
            result = super.getItems(FIND_AUTHORS_BY_ALL, args);
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("ID"));
                author.setName(name);
                author.setSurname(surname);
                authors.add(author);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return authors;
    }

    public Author findSpecificAuthor(String name, String surname) throws DAOException {
        Author author = null;
        try {
            super.makeConnection();
            Object[] args = {name, surname};
            result = super.getItems(FIND_AUTHORS_BY_ALL, args);
            if (result.next()) {
                author = new Author();
                author.setId(result.getInt("ID"));
                author.setName(name);
                author.setSurname(surname);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return author;
    }
}
