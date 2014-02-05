/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.manager;

import java.util.ResourceBundle;

/**
 *
 * @author lor1an
 */
public class PathManager {

    private static PathManager instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла path.properties
    private static final String BUNDLE_NAME = "ua.kpi.manager.path";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String SUCCESSFUL_LOGIN_PAGE_PATH = "SUCCESSFUL_LOGIN_PAGE_PATH";
    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
    public static final String SUCCESFUL_REGISTRATION_PAGE_PATH = "SUCCESSFUL_REGISTRATION_PAGE_PATH";
    public static final String ERROR_LOGIN_PAGE_PATH = "ERROR_LOGIN_PAGE_PATH";
    public static final String CATALOG_PAGE_PATH = "CATALOG_PAGE_PATH";
    public static final String REQUESTS_PAGE_PATH = "REQUESTS_PAGE_PATH";
    public static final String USER_REQUESTS_PAGE_PATH = "USER_REQUESTS_PAGE_PATH";
    public static final String SEARCH_PAGE_PATH = "SEARCH_PAGE_PATH";
    public static final String EDIT_PAGE_PATH = "EDIT_PAGE_PATH";
    public static final String CONFIRM_PAGE_PATH = "CONFIRM_PAGE_PATH";
    public static final String ADD_BOOK_PAGE_PATH = "ADD_BOOK_PAGE_PATH";
    
    public static PathManager getInstance() {
        if (instance == null) {
            instance = new PathManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
