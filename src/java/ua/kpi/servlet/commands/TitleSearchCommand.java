/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Book;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class TitleSearchCommand implements Command {

    public static final String TITLE = "title";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start Title search command;");

        String title = request.getParameter(TITLE);
        DAOFactory dfactory = new DAOFactory(logger);
        BookDAO bdao = dfactory.getBookDAO();
        try {
            Book book = bdao.findByTitle(title);
            List<Book> BookList = new LinkedList<Book>();
            BookList.add(book);
            request.setAttribute("datalist", BookList);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }


        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.CATALOG_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.CATALOG_PAGE_PATH);
    }
}
