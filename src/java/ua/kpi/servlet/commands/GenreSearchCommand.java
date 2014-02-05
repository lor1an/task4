/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.DAO.GenreDAO;
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
import ua.kpi.model.Genre;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class GenreSearchCommand implements Command {

    public static final String GENRE = "genre";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start Genre search command;");

        String genre_name = request.getParameter(GENRE);
        DAOFactory dfact = new DAOFactory(logger);
        GenreDAO gdao = dfact.getGenreDAO();
        BookDAO bdao = dfact.getBookDAO();
        try {
            Genre genre = gdao.findByName(genre_name);
            List<Book> BookList = bdao.findByGenre(genre);
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
