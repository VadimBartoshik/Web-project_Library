<%@ page import="java.sql.Connection" %>
<%@ page import="by.epam.javaweb.bartoshik.library.connection.ConnectionCreator" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.BuyDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.TakeDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>take Book</title>
</head>
<body>
<style>
    <%@include file='css/style take.css' %>
</style>
<p><img src="image/take.jpg" alt="takeBook"></p>
<center>
    <header>
    <h1>Take a book</h1>
    </header>

<%
    ArrayList<Book> books = TakeDao.getAllFreeBookList();
    request.setAttribute("books", books);
%>

    <table border="1">
        <tr>

            <th>title</th>
            <th>author</th>
            <th>take</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>

                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <form class="take_btn" method="post" action="TakeController" >
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="submit" value="Take">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h1><a class="back" href="welcome.jsp">Back</a></h1>
</center>
</body>
</html>