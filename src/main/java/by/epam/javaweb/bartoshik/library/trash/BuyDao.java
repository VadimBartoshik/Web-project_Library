package by.epam.javaweb.bartoshik.library.trash;

import by.epam.javaweb.bartoshik.library.model.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BuyDao {
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

    public static void buyBook(Connection connection, int bookId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM book WHERE id = "+bookId+";");
    }


}
