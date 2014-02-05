/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO.Interfaces;

import ua.kpi.exceptions.DAOException;
import java.util.List;
import ua.kpi.model.Genre;

/**
 *
 * @author lor1an
 */
public interface GenreCRUD extends CRUD<Genre> {

    public List<Genre> getAll() throws DAOException;
    
    public Genre findByName(String name) throws DAOException;
}
