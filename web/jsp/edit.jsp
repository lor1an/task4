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
    <head><title>Edit library</title></head>
    <body>
        <fieldset>
            <legend><h2><c:out value="${parametersMap.get('EditLibrary')}:"/></h2></legend>
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
                        <form name="logoutForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="gtabp"/>
                            <input type="submit" value="${parametersMap.get('AddBook')}" class="subo">
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
                                    <tr>
                                        <th>ID</th>
                                        <th><c:out value="${parametersMap.get('BookTitle')}:"/></th>
                                        <th><c:out value="${parametersMap.get('Author')}:"/></th>
                                        <th><c:out value="${parametersMap.get('Genre')}:"/></th>
                                        <th><c:out value="${parametersMap.get('Stock')}:"/></th>
                                        <th><c:out value="${parametersMap.get('MarkChanged')}:"/></th>
                                    </tr>
                                    <jsp:useBean id="chboxiter" class="ua.kpi.logic.ChboxIter"/>                                    
                                    <c:forEach items="${datalist}" var="list">
                                        <jsp:setProperty name="chboxiter" property="firstIterator" value="${list.id}"/>
                                        <jsp:setProperty name="chboxiter" property="secondIterator" value="${list.id}"/>
                                        <tr>
                                            <td align="center">
                                                <c:out value="${list.id}"/> 
                                            </td>
                                            <td align="center">
                                                <input type="text" name="${list.id}title" value="${list.title}">

                                            </td>
                                            <td align="center">
                                                <input type="text" name="${list.id}author" value="${list.author.wame}">                                                
                                            </td>
                                            <td align="center">
                                                <input type="text" name="${list.id}genre" value="${list.genre.name}">                                                
                                            </td>

                                            <td align="center">
                                                <input type="text" name="${list.id}stock" value="${list.stock}">                                              
                                            </td>
                                            <td align="center">
                                                <INPUT type="checkbox"  name="${chboxiter.checkboxName}" value="${chboxiter.checkboxValue}">
                                            </td>
                                        </tr>	
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td align="center">
                                            <input type="hidden" name="command" value="edit"/>
                                            <input type="submit" value="${parametersMap.get('Change')}" class="subo">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </fieldset>
    </body>

</html>
