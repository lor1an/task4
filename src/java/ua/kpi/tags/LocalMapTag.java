/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.tags;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;
import ua.kpi.local.Localization;

/**
 *
 * @author lor1an
 */
public class LocalMapTag extends TagSupport {

    private HttpServletRequest request;

    public void setRequest(HttpServletRequest request) {
        this.request = (HttpServletRequest) request;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("LocaleCommand") == null) {
            ResourceBundle resourceBundle = Localization.doLocale("en", "US", Localization.LIB_BUNDLENAME);
            HashMap<String, String> parametres = new HashMap();
            for (String paramName : Localization.LIB_NAMES) {
                parametres.put(paramName, resourceBundle.getString(paramName));
            }
            session.setAttribute("parametersMap", parametres);
        }
        return SKIP_BODY;
    }
}
