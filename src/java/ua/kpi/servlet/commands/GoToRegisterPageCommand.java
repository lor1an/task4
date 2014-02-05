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
import ua.kpi.manager.PathManager;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class GoToRegisterPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Start Register command;");
        
        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.REGISTRATION_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.REGISTRATION_PAGE_PATH);
    }
}
