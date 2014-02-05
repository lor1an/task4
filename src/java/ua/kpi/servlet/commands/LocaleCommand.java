/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet.commands;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.local.Localization;
import javax.servlet.jsp.tagext.TagSupport;
import ua.kpi.manager.PathManager;
import ua.kpi.model.BookRequest;
import static ua.kpi.servlet.commands.Command.logger;

/**
 *
 * @author lor1an
 */
public class LocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Start Locale command;");
        Enumeration names = request.getParameterNames();
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle;
        String language = "";
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            language = request.getParameterValues(name)[0];
            session.setAttribute("language", language);
        }
        if (language.equalsIgnoreCase(
                "RU")) {

            resourceBundle = Localization.doLocale("ru", "RU", Localization.LIB_BUNDLENAME);
        } else {
            resourceBundle = Localization.doLocale("en", "US", Localization.LIB_BUNDLENAME);
        }
        HashMap<String, String> parametres = new HashMap();
        for (String paramName : Localization.LIB_NAMES) {
            parametres.put(paramName, resourceBundle.getString(paramName));
            System.out.println(parametres.get(paramName));
        }
        session.setAttribute("parametersMap", parametres);
        session.setAttribute("LocaleCommand", "yes");
        String path = (String) session.getAttribute("CurrentPagePath");
        return (path != null) ? PathManager.getInstance().getProperty(path)
                : PathManager.getInstance().getProperty(PathManager.MAIN_PAGE_PATH);
    }
}
