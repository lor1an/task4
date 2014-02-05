<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ua.kpi.model.Book"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/taglib.tld"%>
<!DOCTYPE html>

<html>
    <link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
    <link rel="stylesheet" type="text/css" href ="jsp/css/log.css">
    <head><title>Add Book</title></head>
    <body>
        <fieldset>
            <legend><h2><c:out value="${parametersMap.get('AddBook')}:"/></h2></legend>
            <table>
                <tr>
                    <td class="search">
                        <br/>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="exsearch">
                            <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                        </form>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="librp">
                            <input type="submit" value="${parametersMap.get('Requests')}" class="subo">
                        </form>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="gtconp">
                            <input type="submit" value="${parametersMap.get('ConfirmReturn')}" class="subo">
                        </form>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="jump"/>
                            <input type="submit" value="${parametersMap.get('Catalog')}" class="subo">
                        </form>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="gtep">
                            <input type="submit" value="${parametersMap.get('EditLibrary')}" class="subo">
                        </form> 
                        <br>
                        <form name="logoutForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="logout">
                            <input type="submit" value="${parametersMap.get('Logout')}" class="subo">
                        </form>
                    </td>
                    <td>
                        <fieldset>
                            <form name="loginForm" method="POST" action="controller">
                                <table class="inner_table" align="center">
                                    <div class="log_form">
                                        <p><span><c:out value="${parametersMap.get('BookTitle')}:"/></span><input type="text" name="Title" value=""><span></span></p>
                                        <p><span><c:out value="${parametersMap.get('AName')}:"/></span><input type="text" name="Authorn" value=""><span></span></p>
                                        <p><span><c:out value="${parametersMap.get('ASurname')}:"/></span><input type="text" name="Authors" value=""><span></span></p>
                                        <p><span><c:out value="${parametersMap.get('Genre')}:"/></span><input type="text" name="Genre" value=""><span></span></p>
                                        <p><span><c:out value="${parametersMap.get('Stock')}:"/></span><input type="text" name="Stock" value=""><span></span></p>
                                        <br>
                                        <form name="loginForm" method="POST" action="controller">                
                                            <input type="hidden" name="command" value="add"/>                
                                            <input type="submit" value="${parametersMap.get('Add')}" class="submit" >                
                                        </form>
                                    </div>
                                </table>
                            </form>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </fieldset>
    </body>

</html>
