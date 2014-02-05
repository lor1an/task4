/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.AuthorDAO;
import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Author;
import ua.kpi.model.Book;
import static ua.kpi.servlet.commands.Command.logger;
import static ua.kpi.servlet.commands.TitleSearchCommand.TITLE;

/**
 *
 * @author lor1an
 */
public class AuthorSearchCommand implements Command {

    public final String ANAME = "authorn";
    public final String ASURNAME = "authors";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Start Author search command;");

        String aName = request.getParameter(ANAME);
        String aSurname = request.getParameter(ASURNAME);

        DAOFactory dfactory = new DAOFactory(logger);
        AuthorDAO adao = dfactory.getAuthorDAO();
        BookDAO bdao = dfactory.getBookDAO();
        List<Author> authors = new ArrayList();
        try {
            if (!aName.equals("") && !aSurname.equals("")) {
                authors = adao.findByAll(aName, aSurname);
            } else {
                if (!aName.equals("")) {
                    authors = adao.findByName(aName);
                } else if (!aSurname.equals("")) {
                    authors = adao.findBySurname(aSurname);
                }
            }
            List<Book> BookList = bdao.findByAuthors(authors);
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
