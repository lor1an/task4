/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.AuthorDAO;
import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.BookRequestDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.DAO.GenreDAO;
import ua.kpi.exceptions.DAOException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.logic.Convert;
import ua.kpi.logic.PageContentMaker;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Author;
import ua.kpi.model.Book;
import ua.kpi.model.BookRequest;
import ua.kpi.model.Genre;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class EditCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Start Edit command;");
        String name;
        Enumeration names = request.getParameterNames();
        DAOFactory dfactory = new DAOFactory(logger);
        BookDAO bookdao = dfactory.getBookDAO();
        AuthorDAO adao = dfactory.getAuthorDAO();
        GenreDAO gdao = dfactory.getGenreDAO();
        while (names.hasMoreElements()) {
            name = (String) names.nextElement();
            if (name.startsWith("check")) {
                String id = name.replaceAll("check", "");
                String title = request.getParameter(id + "title");
                String a = request.getParameter(id + "author");
                String[] wame = a.split(" ");
                String g = request.getParameter(id + "genre");
                String stock = request.getParameter(id + "stock");
                Author author;
                Genre genre;
                try {
                    author = adao.findSpecificAuthor(wame[0], wame[1]);
                    if (author == null) {
                        adao.save(new Author(wame[0], wame[1]));
                        author = adao.findSpecificAuthor(wame[0], wame[1]);
                    }
                    genre = gdao.findByName(g);
                    if (genre == null) {
                        gdao.save(new Genre(g));
                        genre = gdao.findByName(g);
                    }
                    Book book = new Book(Convert.strToInt(id), title, author, genre, Convert.strToInt(stock));
                    bookdao.update(book);
                } catch (DAOException ex) {
                    logger.info("DAOException was thrown");
                    logger.error("Exception was thrown:", ex);
                }
            }
        }
        PageContentMaker.makeCatalogContent(request);
        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.EDIT_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.EDIT_PAGE_PATH);
    }
}
