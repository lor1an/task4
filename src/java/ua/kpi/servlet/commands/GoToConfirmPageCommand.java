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
import ua.kpi.logic.PageContentMaker;
import ua.kpi.manager.PathManager;

/**
 *
 * @author lor1an
 */
public class GoToConfirmPageCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Start Go to confirm page command;");
        HttpSession session = request.getSession(false);
        PageContentMaker.makeConfirmContent(request);
        session.setAttribute("CurrentPagePath", PathManager.CONFIRM_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.CONFIRM_PAGE_PATH);
    }
}
