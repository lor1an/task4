<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href ="jsp/css/log.css">
        <link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
        <title>Hello page</title>
    </head>
    <body>
        <fieldset>
            <h2> <c:out value="${parametersMap.get('LogMessage1')}, "/><c:out value='${sessionScope.name}'/></h2>
                <p>
                    <c:out value="${parametersMap.get('LogMessage2')} "/><c:out value="${parametersMap.get(sessionScope.islibrarian)}"/>
                    </p>
                    <form name="aForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="jump">
                        <input type="submit" value="${parametersMap.get('Catalog')}" class="subo">

                    </form>
                </fieldset>
            </body>
        </html>
