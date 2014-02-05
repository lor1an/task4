/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import ua.kpi.DAO.BookDAO;
import ua.kpi.DAO.DAOFactory;
import ua.kpi.exceptions.DAOException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.logic.PageContentMaker;
import ua.kpi.manager.PathManager;
import ua.kpi.model.Book;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class GoToEditPageCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start Go to edit page command;");        
        PageContentMaker.makeCatalogContent(request);
        HttpSession session = request.getSession(false);
        session.setAttribute("CurrentPagePath", PathManager.EDIT_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.EDIT_PAGE_PATH);
    }
}
