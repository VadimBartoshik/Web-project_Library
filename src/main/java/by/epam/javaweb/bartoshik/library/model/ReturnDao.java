package by.epam.javaweb.bartoshik.library.model;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.model.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReturnDao {
    public static ArrayList<Book> getAllUserBookList(String email) {

        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = ConnectionCreator.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book WHERE userId =" + getUserId(connection, email) + ";");
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
        return books;
    }

    public static int getUserId(Connection connection, String email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM USER WHERE email='" + email + "';");
        resultSet.next();
        return resultSet.getInt(1);
    }
    public static void returnBook(Connection connection, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET userId = null WHERE id = " + bookId + ";");
    }
}
