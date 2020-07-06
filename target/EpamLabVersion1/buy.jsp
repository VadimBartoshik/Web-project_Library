<%@ page import="java.sql.Connection" %>
<%@ page import="by.epam.javaweb.bartoshik.library.connection.ConnectionCreator" %>
<%@ page import="by.epam.javaweb.bartoshik.library.entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.javaweb.bartoshik.library.dao.BuyDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
</head>
<body>
    <h1>List of book</h1>
    <%--<p><img src="image/buy.png" alt="buyBook"></p>--%>
    <%
        ArrayList<Book> books = BuyDao.getAllFreeBook();
        request.setAttribute("books", books);
    %>

    <table border="1">
        <tr>
            <th>id</th>
            <th>title</th>
            <th>author</th>
            <th>buy</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <form method="post" action="BuyController" style="display:inline;">
                         <input type="hidden" name="id" value="${book.id}">
                         <input type="submit" value="Buy">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

