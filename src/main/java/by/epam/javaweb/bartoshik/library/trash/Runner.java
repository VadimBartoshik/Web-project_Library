package by.epam.javaweb.bartoshik.library.trash;


import java.io.FileInputStream;
import java.io.IOException;


import java.sql.*;

public class Runner {
    public static void main(String[] args) {


        try {

            takeBook(ConnectionCreator.getConnection(),4,1);

            printAllTable(ConnectionCreator.getConnection());

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
        ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE userId is null;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " +
                    resultSet.getString(2) + "  " + resultSet.getString(3));
        }
    }

    public static void takeBook(Connection connection, int userId, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET userId = " + userId + " WHERE id = " + bookId + ";");
    }

    public static void printAllBookOfUser(Connection connection, int userId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE userId =" + userId + ";");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " +
                    resultSet.getString(2) + "  " + resultSet.getString(3));
        }
    }

    public static void returnBook(Connection connection, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET userId = null WHERE id = " + bookId + ";");
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
