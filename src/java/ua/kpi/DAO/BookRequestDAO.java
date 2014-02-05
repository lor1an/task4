/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.DAO.Interfaces.BookRequestCRUD;
import ua.kpi.exceptions.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.kpi.model.BookRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class BookRequestDAO extends BasicCRUD implements BookRequestCRUD {

    private static final String ADD_BOOK_REQUEST = "insert into bookrequests(BookTitle, UserLog, Status, Response) values (?,?,?,?);";
    private static final String UPDATE_BOOKREQUEST = "update bookrequests set Response=?, Status=? where ID =?;";
    private static final String GET_ALL_REQUSTS = "select ID, BookTitle, UserLog, Status, Response from bookrequests;";
    private static final String FIND_REQUESTS_BY_USERLOG = "select ID, BookTitle, Status, Response from bookrequests where UserLog=?;";
    private static final String FIND_REQUEST_BY_ID = "select BookTitle,UserLog, Status, Response from bookrequests where ID=?;";
    private static final String FIND_REQUESTS_FOR_RETURN = "select ID,BookTitle,UserLog,Response, Status from bookrequests where Status=? or Status=?;";
    private static final String DELETE_BOOK_REQUEST = "delete from bookrequests where id=?";

    BookRequestDAO(Logger logger) {
        super(logger);
    }

    @Override
    public BookRequest findById(int id) throws DAOException {
        BookRequest bookr = new BookRequest();
        try {
            super.makeConnection();
            Object args[] = {id};
            result = super.getItems(FIND_REQUEST_BY_ID, args);
            if (result.next()) {
                bookr.setId(id);
                bookr.setBookTitle(result.getString("BookTitle"));
                bookr.setStatus(result.getString("Status"));
                bookr.setResponse(result.getString("Response"));
                bookr.setUserLog(result.getString("UserLog"));
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return bookr;
    }

    @Override
    public boolean update(BookRequest entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getResponse(), entity.getStatus(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_BOOKREQUEST, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int save(BookRequest entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getBookTitle(), entity.getUserLog(), entity.getStatus(), entity.getResponse()};
        resultValue = super.executeQuery(ADD_BOOK_REQUEST, args);
        super.closeConnection();
        if (resultValue > 0) {
            return resultValue;
        } else {
            return 0;
        }
    }

    @Override
    public boolean delete(BookRequest entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getId()};
        resultValue = super.executeQuery(DELETE_BOOK_REQUEST, args);
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookRequest> getAll() throws DAOException {
        List<BookRequest> bookrList = new ArrayList<>();
        try {
            super.makeConnection();
            Object args[] = {};
            result = super.getItems(GET_ALL_REQUSTS, args);

            while (result.next()) {
                BookRequest bookr = new BookRequest();
                bookr.setId(result.getInt("ID"));

                bookr.setBookTitle(result.getString("BookTitle"));
                bookr.setStatus(result.getString("Status"));
                bookr.setResponse(result.getString("Response"));
                bookr.setUserLog(result.getString("UserLog"));
                bookrList.add(bookr);
            }

            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return bookrList;
    }

    @Override
    public List<BookRequest> findByLogin(String login) throws DAOException {
        List<BookRequest> bookrList = new ArrayList<>();
        try {
            super.makeConnection();
            Object[] args = {login};
            result = super.getItems(FIND_REQUESTS_BY_USERLOG, args);
            while (result.next()) {
                BookRequest bookr = new BookRequest();
                bookr.setId(result.getInt("Id"));
                bookr.setBookTitle(result.getString("BookTitle"));
                bookr.setStatus(result.getString("Status"));
                bookr.setResponse(result.getString("Response"));
                bookr.setUserLog(login);
                bookrList.add(bookr);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return bookrList;

    }

    public List<BookRequest> findByStatus() throws DAOException {
        List<BookRequest> bookrList = new ArrayList<>();
        try {
            super.makeConnection();
            Object[] args = {"reading-room", "subscription"};
            result = super.getItems(FIND_REQUESTS_FOR_RETURN, args);
            while (result.next()) {
                BookRequest bookr = new BookRequest();
                bookr.setId(result.getInt("ID"));
                bookr.setBookTitle(result.getString("BookTitle"));
                bookr.setStatus(result.getString("Status"));
                bookr.setResponse(result.getString("Response"));
                bookr.setUserLog(result.getString("UserLog"));
                bookrList.add(bookr);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return bookrList;

    }
}