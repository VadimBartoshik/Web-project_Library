
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
    <%@include file='css/style getAll.css' %>
</style>


    <p><img src="image/buy.png" alt="buyBook"></p>
<%--    <%--%>
<%--        ArrayList<Book> books = BuyDao.getAllFreeBookList();--%>
<%--        request.setAttribute("books", books);--%>
<%--    %>--%>
<center>
        <h1>List of book </h1>
    <form class="button_getAll" method="post" action="getAllBook" name="LoginForm" >
        <input type="submit" name="btn_getAll" value="show list">
    </form>
    <table border="1">
        <tr>
            <th>title</th>
            <th>author</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>

                <td>${book.title}</td>
                <td>${book.author}</td>

            </tr>
        </c:forEach>
    </table>

    <h1><a class="back" href="welcome.jsp">Back</a></h1>
</center>
</body>
</html>

