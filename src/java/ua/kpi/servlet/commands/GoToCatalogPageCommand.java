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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GoToCatalogPageCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start GoToCatalogPageCommand command;");
        HttpSession session = request.getSession(false);        
        PageContentMaker.makeCatalogContent(request);
        session.setAttribute("CurrentPagePath", PathManager.CATALOG_PAGE_PATH);
        return PathManager.getInstance().getProperty(PathManager.CATALOG_PAGE_PATH);
    }
}
