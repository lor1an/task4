/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.logic;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.BookRequestDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.model.Book;
import ua.kpi.model.BookRequest;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class PageContentMaker {

    public static void makeCatalogContent(HttpServletRequest request) {
      DAOFactory factory = new DAOFactory(logger);
        BookDAO bookdao = factory.getBookDAO();
        try {
            List<Book> list = bookdao.getAll();
            request.setAttribute("datalist", list);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }
    }

    public static void makeLibPageContent(HttpServletRequest request) {
        DAOFactory factory = new DAOFactory(logger);
        BookRequestDAO bookrdao = factory.getBookRequestDAO();
        try {
            List<BookRequest> list = bookrdao.getAll();
            request.setAttribute("datalist", list);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }
    }

    public static void makeReaderPageContent(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        DAOFactory factory = new DAOFactory(logger);
        BookRequestDAO bookrdao = factory.getBookRequestDAO();
        try {
            List<BookRequest> list = bookrdao.findByLogin((String) session.getAttribute("name"));
            request.setAttribute("datalist", list);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }
    }

    public static void makeConfirmContent(HttpServletRequest request) {
        DAOFactory factory = new DAOFactory(logger);
        BookRequestDAO bookrdao = factory.getBookRequestDAO();
        try {
            List<BookRequest> list = bookrdao.findByStatus();
            request.setAttribute("datalist", list);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }
    }
}
