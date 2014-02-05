/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.BookRequestDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.logic.Convert;
import ua.kpi.logic.PageContentMaker;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Book;
import ua.kpi.model.BookRequest;
import static ua.kpi.servlet.commands.Command.logger;
//

/**
 *
 * @author lor1an
 */
public class ApproveCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Start Approve command;");

        String name, value;
        String pos = "P";
        String neg = "N";
        try {
            Enumeration names = request.getParameterNames();
            DAOFactory dfactory = new DAOFactory(logger);
            BookRequestDAO bookrdao = dfactory.getBookRequestDAO();
            BookDAO bookdao = dfactory.getBookDAO();

            while (names.hasMoreElements()) {
                name = (String) names.nextElement();
                if (name.equals("command")) {
                    continue;
                } else {
                    if (name.startsWith("siterator")) {
                        String replaceAll = name.replaceAll("siterator", "");
                        BookRequest bookr = bookrdao.findById(Convert.strToInt(replaceAll));
                        if (!bookr.getResponse().equals(BookRequest.NEG_RESPONSE)) {
                            bookr.setStatus(BookRequest.STATUS_SUBSCRIPTION);
                            bookrdao.update(bookr);
                        }
                    } else {
                        String replaceAll = name.replaceAll("check", "");
                        BookRequest bookr = bookrdao.findById(Convert.strToInt(replaceAll));
                        value = request.getParameterValues(name)[0];
                        if (value.equalsIgnoreCase(neg)) {
                            bookr.setStatus(BookRequest.STATUS_CLOSED);
                            bookr.setResponse(BookRequest.NEG_RESPONSE);
                            bookrdao.update(bookr);
                        } else {
                            if (!bookr.getStatus().equals(BookRequest.STATUS_SUBSCRIPTION)) {
                                bookr.setStatus(BookRequest.STATUS_READING_ROOM);
                            }
                            Book book = bookdao.findByTitle(bookr.getBookTitle());
                            if (book.getStock() > 0) {
                                book.setStock(book.getStock() - 1);
                                bookdao.updateBookStock(book);
                                bookr.setResponse(BookRequest.POS_RESPONSE);
                            } else {
                                bookr.setStatus(BookRequest.STATUS_CLOSED);
                                bookr.setResponse(BookRequest.NEG_RESPONSE);
                            }
                            bookrdao.update(bookr);
                        }
                    }
                }
            }

        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }

        PageContentMaker.makeLibPageContent(request);
        HttpSession session = request.getSession(false);
        session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.REQUESTS_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.REQUESTS_PAGE_PATH);

    }
}
