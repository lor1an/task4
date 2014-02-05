/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.local;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author lor1an
 */
public class Localization {

    private static Localization instance;
    //класс извлекает информацию из файла messages.properties
    public static final String LIB_BUNDLENAME = "ua.kpi.local.lib";
    //название локализованых переменных
    public static final String[] LIB_NAMES = {"PleaseLogin", "Username", "Password",
        "Login", "Register", "Lang", "Local", "CatalogTitle", "Search", "Requests",
        "EditLibrary", "MyRequests", "Logout", "BookTitle", "Author", "Genre",
        "Stock", "Order", "Submit", "Reader", "Librarian", "Catalog", "ErrorLogMessage", "SucRegMessage",
        "MainPage", "AddBook", "Add","MarkChanged", "Change", "RequestsTitle", "UserRequestsTitle",
        "Status", "Response", "Reply", "Subscription", "Approve", "AName",
        "ASurname", "TitleSearchM", "AuthorSearchM", "GenreSearchM", "LogMessage1",
        "LogMessage2", "Name", "Surname", "PleaseRegister", "ConfirmPageTitle", "ConfirmReturn", "Confirm"
    };

    public static ResourceBundle doLocale(String language, String country,
            String bundleName) {
        return ResourceBundle.getBundle(bundleName, new Locale(language, country));
    }

    public static Localization getInstance() {
        if (instance == null) {
            instance = new Localization();
        }
        return instance;
    }
}
