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
    <head><title>Requests</title></head>
    <body>
        <fieldset>
            <legend><h2><c:out value="${parametersMap.get('RequestsTitle')}:"/></h2></legend>
            <table>
                <tr>
                    <td class="search">
                        <br/>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="exsearch">
                            <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                            <br/>
                        </form>
                        <form name="aForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="jump">
                            <input type="submit" value="${parametersMap.get('Catalog')}" class="subo">
                        </form>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="gtconp">
                            <input type="submit" value="${parametersMap.get('ConfirmReturn')}" class="subo">
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
                                    <tr>
                                        <th><c:out value="${parametersMap.get('BookTitle')}"/></th>
                                        <th><c:out value="${parametersMap.get('Username')}"/></th>
                                        <th><c:out value="${parametersMap.get('Status')}"/></th>
                                        <th><c:out value="${parametersMap.get('Response')}"/></th>
                                        <th><c:out value="${parametersMap.get('Reply')}"/></th>
                                        <th><c:out value="${parametersMap.get('Subscription')}"/></th>
                                    </tr>
                                    <jsp:useBean id="chboxiter" class="ua.kpi.logic.ChboxIter"/>
                                    <c:forEach items="${datalist}" var="list">
                                        <jsp:setProperty name="chboxiter" property="firstIterator" value="${list.id}"/>
                                        <jsp:setProperty name="chboxiter" property="secondIterator" value="${list.id}"/>
                                        <jsp:setProperty name="chboxiter" property="thirdIterator" value="${list.id}"/>
                                        <tr>
                                            <td align="center">
                                                <c:out value="${list.bookTitle}"/> 
                                            </td>
                                            <td align="center">
                                                <c:out value="${list.userLog}"/> 
                                            </td>
                                            <td align="center">
                                                <c:out value="${list.status}"/> 
                                            </td>

                                            <td align="center">

                                                <c:out value="${list.response}"/> 
                                            </td>
                                            <c:if test="${list.response eq 'unchecked'}">
                                                <td align="center">

                                                    <TABLE> <tr> 
                                                            <td>+</td><td><INPUT type="radio"  
                                                                                 name="${chboxiter.checkboxName}" 
                                                                                 value="P"></td> 
                                                            <td>-</td><td><INPUT type="radio" 
                                                                                 name="${chboxiter.checkboxNamee}" 
                                                                                 value="N"></td> 
                                                        </tr></TABLE>

                                                </td>
                                                <td align="center">
                                                    <INPUT type="radio"  name="${chboxiter.SIterator}" value="subscript">

                                                </td>
                                            </c:if>

                                        </tr>	
                                    </c:forEach>

                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td align="center">
                                            <input type="hidden" name="command" value="appr"/>
                                            <input type="submit" value="${parametersMap.get('Approve')}" class="subo">

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
