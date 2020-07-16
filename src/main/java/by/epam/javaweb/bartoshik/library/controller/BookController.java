package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class BookController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    private BaseDao<Book, Integer> dao;

    public void init() {
        DaoFactory<Connection> factory = new MySqlDaoFactory();
        try {
            Connection connection = factory.getContext();
            dao = factory.getDao(connection, Book.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        final String ADD_USER_BOOK = "/addBook";
        final String DELETE_USER_BOOK = "/deleteBook";
        final String GET_ALL_USER_BOOK = "/getAllBook";
        final String UPDATE_USER_BOOK = "/updateBook";

        String action = request.getServletPath();
        Book book;
        Integer bookId;
        try {
            switch (action) {
                case ADD_USER_BOOK:
                    book = getBookFromJsp(request);
                    dao.create(book);
                    break;
                case DELETE_USER_BOOK:
                    bookId = getBookIdFromJsp(request);
                    dao.delete(bookId);
                    break;
                case GET_ALL_USER_BOOK:
                    dao.getAll();
                    break;
                case UPDATE_USER_BOOK:
                    bookId = getBookIdFromJsp(request);
                    dao.update(bookId);
                    break;
            }
        } catch (PersistException exception) {
            throw new ServletException(exception);
        }
    }

    private Book getBookFromJsp(HttpServletRequest request) {
        String title = request.getParameter("txt_title");
        String author = request.getParameter("txt_author");
        return new Book(title, author);
    }

    private Integer getBookIdFromJsp(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
