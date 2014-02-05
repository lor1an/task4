<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/taglib.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" type="text/css" href ="jsp/css/log.css">
        <link rel="stylesheet" type="text/css" href ="jsp/css/catalog.css">
    </head>
    <body>
        <fieldset>           
            <div class="log_form">
                <fieldset>
                    <form name="SearchForm" method="POST" action="controller">
                        <legend><H2><c:out value="${parametersMap.get('TitleSearchM')}:"/></H2></legend>
                        <p><span><c:out value="${parametersMap.get('BookTitle')}:"/></span><input type="text" name="title" value=""><span></span></p>
                        <input type="hidden" name="command" value="tsc">
                        <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                    </form>
                </fieldset>            
                <fieldset>                            
                    <form name="SearchForm" method="POST" action="controller">
                        <legend><H2><c:out value="${parametersMap.get('AuthorSearchM')}:"/></H2></legend>
                        <p><span><c:out value="${parametersMap.get('AName')}:"/></span><input type="text" name="authorn" value=""><span></span></p>
                        <p><span><c:out value="${parametersMap.get('ASurname')}:"/></span><input type="text" name="authors" value=""><span></span></p>
                        <input type="hidden" name="command" value="asc">
                        <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                    </form>
                </fieldset>            
                <fieldset> 
                    <form name="SearchForm" method="POST" action="controller"> 
                        <legend><H2><c:out value="${parametersMap.get('GenreSearchM')}:"/></H2></legend>
                        <p><span><c:out value="${parametersMap.get('Genre')}:"/></span><input type="text" name="genre" value=""><span></span></p>
                        <br/>
                        <input type="hidden" name="command" value="gsc">
                        <input type="submit" value="${parametersMap.get('Search')}" class="subo">
                    </form>
                </fieldset>            
            </div>
        <br>
        <form name="aForm" method="POST" action="controller">
            <input type="hidden" name="command" value="jump">
            <input type="submit" value="${parametersMap.get('Catalog')}" class="subo">
        </form>
        <form name="logoutForm" method="POST" action="controller">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="${parametersMap.get('Logout')}" class="subo">
        </form>
    </fieldset>
</body>
</html>
