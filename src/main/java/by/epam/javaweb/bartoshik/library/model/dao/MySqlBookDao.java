package by.epam.javaweb.bartoshik.library.model.dao;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseJDBCDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlBookDao extends BaseJDBCDao<Book, Integer> {

    private static final String GET_UPDATE_QUERY = "UPDATE book SET userId = ? WHERE id =?;";
    private static final String GET_SELECT_QUERY = "SELECT id, title, author FROM book;";
    private static final String GET_CREATE_QUERY = "INSERT INTO book (title, author) VALUES (?,?);";
    private static final String GET_DELETE_QUERY = "DELETE FROM book WHERE id=?;";

    public MySqlBookDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    public String getSelectQuery() {
        return GET_SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return GET_CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return GET_UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return GET_DELETE_QUERY;
    }



    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws PersistException {

        List<Book> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
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
    protected void prepareStatementForUpdate(PreparedStatement statement, String userId, Integer bookId) throws PersistException {
        try {
            logger.info("prepareStatementForUpdate method started");
            logger.info("userId is - " + userId);
            logger.info("bookId is - " + bookId);
            statement.setString(1, userId);
            statement.setInt(2, bookId);
        } catch (SQLException exception) {
            logger.info("exception in prepareStatementForUpdate method");
            throw new PersistException(exception);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Integer key) throws PersistException {
        try {
            statement.setInt(1, key);
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }
}

