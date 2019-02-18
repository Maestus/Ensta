<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
    <center>
        <h1>Books Management</h1>
        <h2>
            <a href="/new">Ajout de nouveau film</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">Liste de films</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${film != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${film == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${film != null}">
                        modifier film
                    </c:if>
                    <c:if test="${film == null}">
                        Ajout de nouveau film
                    </c:if>
                </h2>
            </caption>
                <c:if test="${film != null}">
                    <input type="hidden" name="id" value="<c:out value='${film.id}' />" />
                </c:if>           
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="titre" size="45"
                            value="<c:out value='${film.titre}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                    <input type="text" name="realisateur" size="45"
                            value="<c:out value='${film.realisateur}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>