<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ua.kpi.model.Book"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/taglib.tld" %>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
    <link rel="stylesheet" type="text/css" href ="jsp/css/log.css">
    <head><title>Request</title></head>
    <body>
        <fieldset>
            <legend><h2><c:out value="${parametersMap.get('UserRequestsTitle')}:"/></h2></legend>
            <table>
                <tr>
                    <td class="search">
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="exsearch">
                            <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                            <br/>
                        </form>

                        <form name="aForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="jump">
                            <input type="submit" value="${parametersMap.get('Catalog')}" class="subo">
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
                                        <th><c:out value="${parametersMap.get('BookTitle')}"/>Title</th>
                                        <th><c:out value="${parametersMap.get('Status')}"/></th>
                                        <th><c:out value="${parametersMap.get('Response')}"/></th>                                        
                                    </tr>
                                    <jsp:useBean id="chboxiter" class="ua.kpi.logic.ChboxIter"/>
                                    <c:forEach items="${datalist}" var="list">
                                        <tr>
                                            <td align="center">
                                                <c:out value="${list.bookTitle}"/> 
                                            </td>
                                            <td align="center">
                                                <c:out value="${list.status}"/> 
                                            </td>
                                            <td align="center">
                                                <c:out value="${list.response}"/> 
                                            </td>
                                        </tr>	
                                    </c:forEach>


                                </table>
                            </form>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </fieldset>
    </body>

</html>
