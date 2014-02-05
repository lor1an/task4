/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import java.io.IOException;
import java.util.Enumeration;
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
public class GoToSearchPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Start GoToSearchPageCommand command;");
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println("nameee" + name);
            String value = request.getParameterValues(name)[0];
            System.out.println("valuee" + value);
            

        }
        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.SEARCH_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.SEARCH_PAGE_PATH);
    }
}
