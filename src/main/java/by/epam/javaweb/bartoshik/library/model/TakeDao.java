package by.epam.javaweb.bartoshik.library.model;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.model.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TakeDao {

    public static ArrayList<Book> getAllFreeBookList() {

        ArrayList<Book> books = new ArrayList<Book>();

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
        return books;
    }

    public static void takeBook(Connection connection, int bookId, String userEmail) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE book SET userId = " + getUserId(connection, userEmail) + " WHERE id = " + bookId + ";");
    }

    public static int getUserId(Connection connection, String email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM USER WHERE email='" + email + "';");
        resultSet.next();
        return resultSet.getInt(1);
    }
}
