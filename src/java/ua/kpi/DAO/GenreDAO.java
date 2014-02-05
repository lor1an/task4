/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.DAO.Interfaces.GenreCRUD;
import ua.kpi.exceptions.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.kpi.model.Genre;
import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class GenreDAO extends BasicCRUD implements GenreCRUD {

    private static final String FIND_GENRE_BY_ID = "select Name from genres where ID = ?";
    private static final String FIND_GENRE_BY_NAME = "select ID from genres where Name = ?;";
    private static final String ADD_GENRE = "insert into genres (Name) values (?);";
    private static final String UPDATE_GENRE = "update genres set name=? where id=?;";
    private static final String GET_ALL_GENRES = "select ID, Name from  genres;";
    private static final String DELETE_GENRE = "delete from genres where id=?";

    GenreDAO(Logger logger) {
        super(logger);
    }

    @Override
    public Genre findById(int id) throws DAOException {
        Genre genre = null;
        try {
            super.makeConnection();
            Object[] args = {id};
            result = super.getItems(FIND_GENRE_BY_ID, args);
            if (result.next()) {
                genre = new Genre();
                genre.setId(id);
                genre.setName(result.getString("Name"));
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return genre;
    }

    @Override
    public boolean update(Genre entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_GENRE, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int save(Genre entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getName()};
        resultValue = super.executeQuery(ADD_GENRE, args);
        super.closeConnection();
        if (resultValue > 0) {
            return resultValue;
        } else {
            return 0;
        }
    }

    @Override
    public boolean delete(Genre entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getId()};
        resultValue = super.executeQuery(DELETE_GENRE, args);
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Genre> getAll() throws DAOException {
        List<Genre> genres = new ArrayList<>();
        try {
            super.makeConnection();
            Object args[] = {};
            result = super.getItems(GET_ALL_GENRES, args);
            while (result.next()) {
                Genre genre = new Genre();
                genre.setId(result.getInt("Id"));
                genre.setName(result.getString("Name"));
                genres.add(genre);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!", ex);
        }
        return genres;
    }

    @Override
    public Genre findByName(String name) throws DAOException {
        Genre genre = null;
        try {
            super.makeConnection();
            Object[] args = {name};
            result = super.getItems(FIND_GENRE_BY_NAME, args);
            if (result.next()) {
                genre = new Genre();
                genre.setId(result.getInt("ID"));
                genre.setName(name);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return genre;
    }
}
