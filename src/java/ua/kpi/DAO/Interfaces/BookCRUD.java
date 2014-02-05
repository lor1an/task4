/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO.Interfaces;

import ua.kpi.exceptions.DAOException;
import java.util.List;
import ua.kpi.model.Author;
import ua.kpi.model.Book;
import ua.kpi.model.Genre;

/**
 *
 * @author lor1an
 */
public interface BookCRUD extends CRUD<Book> {

    public List<Book> getAll() throws DAOException;

    public Book findByTitle(String title) throws DAOException;

    public List<Book> findByAuthor(Author author) throws DAOException;

    public List<Book> findByGenre(Genre genre) throws DAOException;
}
