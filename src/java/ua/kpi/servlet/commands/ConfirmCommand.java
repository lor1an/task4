/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.BookRequestDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
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

/**
 *
 * @author lor1an
 */
public class ConfirmCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start Confirm command;");
        String name;
        Enumeration names = request.getParameterNames();
        DAOFactory dfactory = new DAOFactory(logger);
        BookRequestDAO bookrdao = dfactory.getBookRequestDAO();
        BookDAO bookdao = dfactory.getBookDAO();
        while (names.hasMoreElements()) {
            name = (String) names.nextElement();
            if (name.equals("command")) {
                continue;
            } else {
                String replaceAll = name.replaceAll("check", "");
                try {
                    BookRequest bookr = bookrdao.findById(Convert.strToInt(replaceAll));
                    bookr.setStatus(BookRequest.STATUS_CLOSED);
                    bookrdao.update(bookr);
                    Book book = bookdao.findByTitle(bookr.getBookTitle());
                    book.setStock(book.getStock() + 1);
                    bookdao.updateBookStock(book);
                } catch (DAOException ex) {
                    logger.info("DAOException was thrown");
                    logger.error("Exception was thrown:", ex);
                }
            }
        }
        HttpSession session = request.getSession(false);
        PageContentMaker.makeConfirmContent(request);
        session.setAttribute("CurrentPagePath", PathManager.CONFIRM_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.CONFIRM_PAGE_PATH);
    }
}
