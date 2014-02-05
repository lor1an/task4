<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/taglib.tld" %>
<!DOCTYPE html>
<html>
    <t:maptag request="${pageContext.request}"/>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href ="jsp/css/log.css">
    </head>
    <body>
        <fieldset>
            <legend><H2><c:out value="${parametersMap.get('PleaseLogin')}:"/></H2></legend>
            <form name="loginForm" method="POST" action="controller">
                <div class="log_form">
                    <p><span><c:out value="${parametersMap.get('Username')}:"/></span><input type="text" name="login" value=""><span></span></p>
                    <p><span><c:out value="${parametersMap.get('Password')}:"/></span><input type="password" name="password" value=""><span></span></p>
                </div>
                <br/>
                <input type="hidden" name="command" value="login">
                <input type="submit" value="${parametersMap.get('Login')}" class="submit" style="width: 93px;" >
            </form>
            <table>
                <tr>
                    <td align="left">
                        <form name="loginForm" method="POST" action="controller">                
                            <input type="hidden" name="command" value="register"/>                
                            <input type="submit" value="${parametersMap.get('Register')}" class="submit" >                
                        </form>
                    </td>
                    <td align="right">
                        <form name="loginForm" method="POST" action="controller" >                
                            <input type="hidden" name="command" value="${parametersMap.get('Lang')}" />                
                            <input type="submit" value="${parametersMap.get('Local')}" class="submit">                
                        </form>
                    </td>
                </tr>
            </table>
        </fieldset>
    </body>
</html>
