<%@ page import="java.sql.Connection" %>
<%@ page import="by.epam.javaweb.bartoshik.library.trash.ConnectionCreator" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.javaweb.bartoshik.library.trash.BuyDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.trash.TakeDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.factory.DaoFactory" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao" %>
<%@ page import="by.epam.javaweb.bartoshik.library.model.exeption.PersistException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
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
    BaseDao dao = null;
    DaoFactory<Connection> factory = new MySqlDaoFactory();
    try {
        Connection connection = factory.getContext();
        dao = factory.getDao(connection, Book.class);
    } catch (PersistException e) {
        e.printStackTrace();
    }
    ArrayList<Book> books = new ArrayList<>();

    try (Connection connection = ConnectionCreator.getConnection()) {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE userId is null;");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            Book book = new Book(id, title, author);
            books.add(book);
        }
    } catch (Exception ex) {
        System.out.println(ex);
    }
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
                    <form class="take_btn" method="post" action="takeBook" >
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