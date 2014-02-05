<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successful!</title>
    </head>
    <body>
        <fieldset>
            <h1><c:out value="${parametersMap.get('SucRegMessage')}:"/></h1>
            <form name="logoutForm" method="POST" action="controller">
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="Main Page">
            </form>
        </fieldset>
    </body>
</html>
