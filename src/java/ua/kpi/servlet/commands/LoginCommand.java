/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.BookRequestDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.DAO.Interfaces.BookCRUD;
import ua.kpi.DAO.UserDAO;
import ua.kpi.exceptions.DAOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Book;
import ua.kpi.model.BookRequest;
import ua.kpi.model.User;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class LoginCommand implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Start Login command;");

        //извлечение из запроса логина и пароля
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        HttpSession session = request.getSession(false);
        session.setAttribute("name", login);


        DAOFactory dfactory = new DAOFactory(logger);
        UserDAO userdao = dfactory.getUsetDAO();
        //проверка логина и пароля

        try {
            User user = userdao.findByLogin(login);


            if (user == null || !user.getPassword().equals(pass)) {
                return PathManager.getInstance().getProperty(PathManager.ERROR_LOGIN_PAGE_PATH);
            } else {
                if (user.isIsLibrarian() == true) {
                    session.setAttribute("islibrarian", "Librarian");
                } else {
                    session.setAttribute("islibrarian", "Reader");
                }
            }

        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }
        session.setAttribute("CurrentPagePath", PathManager.SUCCESSFUL_LOGIN_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.SUCCESSFUL_LOGIN_PAGE_PATH);
    }
}
