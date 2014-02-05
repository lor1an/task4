/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO.Interfaces;

import java.util.List;
import ua.kpi.exceptions.DAOException;
import ua.kpi.model.BookRequest;

/**
 *
 * @author lor1an
 */
public interface BookRequestCRUD extends CRUD <BookRequest> {
        
      public List<BookRequest> getAll() throws DAOException;
      
      public List<BookRequest> findByLogin(String login) throws DAOException;
}
