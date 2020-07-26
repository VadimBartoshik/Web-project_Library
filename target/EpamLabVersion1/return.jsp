<%@ page import="java.sql.Connection" %>
<%@ page import="by.epam.javaweb.bartoshik.library.connection.ConnectionCreator" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.BuyDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.TakeDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.ReturnDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>return Book</title>
</head>
<body>
<style>
    <%@include file='css/style take.css' %>
</style>
<p><img src="image/take.jpg" alt="takeBook"></p>
<center>
    <header>
    <h1>Return a book</h1>
    </header>

<%
    String userEmail = (String) session.getAttribute("login");
    ArrayList<Book> books = ReturnDao.getAllUserBookList(userEmail);
    request.setAttribute("books", books);
%>

    <table border="1">
        <tr>

            <th>title</th>
            <th>author</th>
            <th>return</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>

                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <form class="take_btn" method="post" action="ReturnController" >
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="submit" value="Return">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h1><a class="back" href="welcome.jsp">Back</a></h1>
</center>
</body>
</html>