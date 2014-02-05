/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.DAO.AuthorDAO;
import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.DAO.GenreDAO;
import ua.kpi.exceptions.DAOException;
import ua.kpi.logic.Convert;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Author;
import ua.kpi.model.Book;
import ua.kpi.model.Genre;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class AddBookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start GoToAddBookPage command;");
        DAOFactory dfactory = new DAOFactory(logger);
        BookDAO bookdao = dfactory.getBookDAO();
        AuthorDAO adao = dfactory.getAuthorDAO();
        GenreDAO gdao = dfactory.getGenreDAO();

        String title = request.getParameter("Title");
        String authorName = request.getParameter("Authorn");
        String authorSurname = request.getParameter("Authors");
        String genreName = request.getParameter("Genre");
        String stock = request.getParameter("Stock");

        Author author;
        Genre genre;
        try {
            author = adao.findSpecificAuthor(authorName, authorSurname);
            if (author == null) {
                adao.save(new Author(authorName, authorSurname));
                author = adao.findSpecificAuthor(authorName, authorSurname);
            }
            genre = gdao.findByName(genreName);
            if (genre == null) {
                gdao.save(new Genre(genreName));
                genre = gdao.findByName(genreName);
            }
            Book book = new Book(title, author, genre, Convert.strToInt(stock));
            bookdao.save(book);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }

        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.ADD_BOOK_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.ADD_BOOK_PAGE_PATH);
    }
}
