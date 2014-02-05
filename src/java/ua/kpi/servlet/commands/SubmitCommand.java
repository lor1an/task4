/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.DAOFactory;
import ua.kpi.DAO.UserDAO;
import ua.kpi.exceptions.DAOException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kpi.manager.PathManager;
import ua.kpi.model.User;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class SubmitCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Start Submit command;");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("username");
        String password = request.getParameter("pass");

        User user = new User(name, surname, login, password, false);
        DAOFactory dfactory = new DAOFactory(logger);
        UserDAO userdao = dfactory.getUsetDAO();
        try {
            userdao.save(user);
        } catch (DAOException ex) {
            logger.info("DAOException was thrown");
            logger.error("Exception was thrown:", ex);
        }

        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.SUCCESFUL_REGISTRATION_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.SUCCESFUL_REGISTRATION_PAGE_PATH);
    }
}