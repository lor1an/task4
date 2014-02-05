/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.servlet;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.servlet.commands.AddBookCommand;
import ua.kpi.servlet.commands.ApproveCommand;
import ua.kpi.servlet.commands.AuthorSearchCommand;
import ua.kpi.servlet.commands.Command;
import ua.kpi.servlet.commands.ConfirmCommand;
import ua.kpi.servlet.commands.EditCommand;
import ua.kpi.servlet.commands.GoToSearchPageCommand;
import ua.kpi.servlet.commands.GenreSearchCommand;
import ua.kpi.servlet.commands.GoToAddBookPageCommand;
import ua.kpi.servlet.commands.GoToLibPageCommand;
import ua.kpi.servlet.commands.GoToReaderPageCommand;
import ua.kpi.servlet.commands.GoToConfirmPageCommand;
import ua.kpi.servlet.commands.GoToEditPageCommand;
import ua.kpi.servlet.commands.GoToCatalogPageCommand;
import ua.kpi.servlet.commands.LocaleCommand;
import ua.kpi.servlet.commands.LoginCommand;
import ua.kpi.servlet.commands.LogoutCommand;
import ua.kpi.servlet.commands.NoCommand;
import ua.kpi.servlet.commands.OrderCommand;
import ua.kpi.servlet.commands.GoToRegisterPageCommand;
import ua.kpi.servlet.commands.SubmitCommand;
import ua.kpi.servlet.commands.TitleSearchCommand;

/**
 *
 * @author lor1an
 */
public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        //заполнение таблицы командами
        commands.put("login", new LoginCommand());
        commands.put("register", new GoToRegisterPageCommand());
        commands.put("submit", new SubmitCommand());
        commands.put("order", new OrderCommand());
        commands.put("exsearch", new GoToSearchPageCommand());
        commands.put("jump", new GoToCatalogPageCommand());
        commands.put("readp", new GoToReaderPageCommand());
        commands.put("librp", new GoToLibPageCommand());
        commands.put("appr", new ApproveCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("asc", new AuthorSearchCommand());
        commands.put("gsc", new GenreSearchCommand());
        commands.put("tsc", new TitleSearchCommand());
        commands.put("gtep", new GoToEditPageCommand());
        commands.put("edit", new EditCommand());
        commands.put("EN", new LocaleCommand());
        commands.put("RU", commands.get("EN"));
        commands.put("conf", new ConfirmCommand());
        commands.put("gtconp", new GoToConfirmPageCommand());
        commands.put("gtabp", new GoToAddBookPageCommand());
        commands.put("add", new AddBookCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        //извлечение команды из запроса
        String action = request.getParameter("command");
        //получение объекта, соответствующего команде
        Command command = commands.get(action);
        if (command == null) {
            //если команды не существует в текущем объекте
            command = new NoCommand();
        }
        return command;
    }
    //создание единственного объекта по шаблону Singleton

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
