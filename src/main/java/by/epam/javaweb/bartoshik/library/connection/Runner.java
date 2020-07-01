package by.epam.javaweb.bartoshik.library.connection;

import java.io.IOException;
import java.sql.*;

import static by.epam.javaweb.bartoshik.library.connection.ConnectionCreator.getConnection;

public class Runner {
    public static void main(String[] args) {
        try {

            buyBook(ConnectionCreator.getConnection(),8);
            System.out.println("Connection to Store DB success full!");

        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void printAllTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

    public static void printAllFreeBook(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE user is null;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " +
                    resultSet.getString(2) + "  " + resultSet.getString(3));
        }
    }

    public static void takeBook(Connection connection, int userId, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET user = " + userId + " WHERE id = " + bookId + ";");
    }

    public static void printAllBookOfUser(Connection connection, int userId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE user =" + userId + ";");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " +
                    resultSet.getString(2) + "  " + resultSet.getString(3));
        }
    }

    public static void returnBook(Connection connection, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET user = null WHERE id = " + bookId + ";");
    }

    public static void presentBook(Connection connection, String title, String author) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO book (title, author) VALUES ('" + title + "', '" + author + "');");
    }

    public static void buyBook(Connection connection, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM book WHERE id = "+bookId+";");
    }




}
