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

/**
 *
 * @author lor1an
 */
public class OrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Start Order command;");

        String name, value;
        String result = "";
        HttpSession session = request.getSession(false);
        try {

            Enumeration names = request.getParameterNames();
            while (names.hasMoreElements()) {
                name = (String) names.nextElement();
                value = request.getParameterValues(name)[0];
                result = result + value;
            }
            String replaceAll = result.replaceAll("order", "");
            String[] split = replaceAll.split("checkbox");
            DAOFactory dfactory = new DAOFactory(logger);
            BookDAO bookdao = dfactory.getBookDAO();
            BookRequestDAO bookrdao = dfactory.getBookRequestDAO();

            for (int y = 1; y < split.length; y++) {
                Book book = bookdao.findById(Convert.strToInt(split[y]));
                BookRequest bookr;
                bookr = new BookRequest(book.getTitle(), (String) session.getAttribute("name"),
                        BookRequest.STATUS_OPEN, BookRequest.UN_RESPONSE);
                bookrdao.save(bookr);
            }


        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }

        PageContentMaker.makeCatalogContent(request);
        session.setAttribute("CurrentPagePath", PathManager.CATALOG_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.CATALOG_PAGE_PATH);
    }
}
