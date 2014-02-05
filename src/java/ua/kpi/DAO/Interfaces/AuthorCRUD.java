/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO.Interfaces;

import ua.kpi.exceptions.DAOException;
import java.util.List;
import ua.kpi.model.Author;
import ua.kpi.model.User;

/**
 *
 * @author lor1an
 */
public interface AuthorCRUD extends CRUD<Author> {

    public List<Author> getAll() throws DAOException;

    public List<Author> findByName(String surname) throws DAOException;;

    public List<Author> findBySurname(String surname) throws DAOException;;

    public List<Author> findByAll(String name, String surname) throws DAOException;;
}
