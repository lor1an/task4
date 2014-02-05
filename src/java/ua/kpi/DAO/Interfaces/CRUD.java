/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO.Interfaces;

import ua.kpi.exceptions.DAOException;

/**
 *
 * @author lor1an
 */
public interface CRUD<T> {

    public T findById(int id)
            throws DAOException;

    public boolean update(T entity)
            throws DAOException;

    public int save(T entity)
            throws DAOException;

    public boolean delete(T entity)
            throws DAOException;
}