<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

    <head>
        <Title>Register Form</title>
        <link rel="stylesheet" type="text/css" href ="jsp/css/reg.css">
    </head>
    <fieldset>
        <legend><H2><c:out value="${parametersMap.get('PleaseRegister')}:"/></H2></legend>
        <form name="register" method="post" autocomplete="off" accept-charset="utf-8" action="controller">
            <div class="form">
                <p><span><c:out value="${parametersMap.get('Name')}:"/></span><input type="name" name="name"  required pattern="[A-Z]{1}[a-z]+"><span></span><span></span></p>
                <p><span><c:out value="${parametersMap.get('Surname')}:"/></span><input type="surname" name="surname"  required pattern="[A-Z]{1}[a-z]{2,}"><span></span><span></span></p>
                <p><span><c:out value="${parametersMap.get('Username')}:"/></span><input type="username" name="username"  required pattern ="[a-zA-Z]{1}[a-zA-Z1-9]{3,20}$"><span></span><span></span></p>
                <p><span><c:out value="${parametersMap.get('Password')}:"/></span><input type="password" name="pass" required pattern ="(.){6,}"><span></span><span></span></p>               

                <p> <span></span></p>

            </div>
            <input type="hidden" name="command" value="submit"/>                
            <input type="submit" value="${parametersMap.get('Submit')}" class="subo">                

        </form>

    </fieldset>

</html>
