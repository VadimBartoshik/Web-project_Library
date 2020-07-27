<%@ page import="by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.factory.DaoFactory" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.entity.Book" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.exeption.PersistException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
</head>
<body>
<style>
    <%@include file='css/style buy.css' %>
</style>


    <p><img src="image/buy.png" alt="buyBook"></p>
    <%
        BaseDao dao = null;
        DaoFactory<Connection> factory = new MySqlDaoFactory();
        try {
            Connection connection = factory.getContext();
            dao = factory.getDao(connection, Book.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        request.setAttribute("books", dao.getAll());
    %>
<center>
        <h1>List of book </h1>
    <table border="1">
        <tr>
            <th>title</th>
            <th>author</th>
            <th>buy</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>

                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <form class="buy_btn" method="post" action="deleteBook" >
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="submit" value="Buy">
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>

    <h1><a class="back" href="welcome.jsp">Back</a></h1>
</center>
</body>
</html>

