package by.epam.javaweb.bartoshik.library.model.dao;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseJDBCDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlBookDao extends BaseJDBCDao<Book, Integer> {
    public MySqlBookDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title, author FROM book WHERE userId is null;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO book (title, author) VALUES (?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE book SET userId = null WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM book WHERE id= ?;";
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
        List<Book> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                result.add(book);
            }
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Book book) throws PersistException {
        try {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Integer key) throws PersistException {
        try {
            statement.setInt(1, key);
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }
}

