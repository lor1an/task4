/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class DAOFactory {

    static Logger logger = Logger.getLogger(DAOFactory.class);

    public DAOFactory(Logger log) {
        logger = log;
    }

    public BookDAO getBookDAO() {
        return new BookDAO(logger);
    }

    public UserDAO getUsetDAO() {
        return new UserDAO(logger);
    }

    public BookRequestDAO getBookRequestDAO() {
        return new BookRequestDAO(logger);
    }

    public AuthorDAO getAuthorDAO() {
        return new AuthorDAO(logger);
    }

    public GenreDAO getGenreDAO() {
        return new GenreDAO(logger);
    }
}