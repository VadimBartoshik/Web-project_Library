package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    private BaseDao dao;

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
            throws ServletException, UnsupportedEncodingException {

        final String ADD_BOOK = "/addBook";
        final String DELETE_BOOK = "/deleteBook";
        final String GET_ALL_BOOK = "/getAllBook";
        final String RETURN_BOOK = "/returnBook";
        final String TAKE_BOOK = "/takeBook";
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        logger.info(action);
        Book book;
        Integer bookId;
        RequestDispatcher requestDispatcher;
        try {
            switch (action) {
                case ADD_BOOK:
                    book = getBookFromJsp(request);
                    dao.create(book);
                    requestDispatcher = request.getRequestDispatcher("present.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case DELETE_BOOK:
                    bookId = getBookIdFromJsp(request);
                    logger.info(bookId);
                    dao.delete(bookId);
                    requestDispatcher = request.getRequestDispatcher("buy.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case GET_ALL_BOOK:
                    request.setAttribute("books", dao.getAll());
                    requestDispatcher = request.getRequestDispatcher("getAll.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case RETURN_BOOK:
                    bookId = getBookIdFromJsp(request);
                    dao.update(bookId);
                    requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case TAKE_BOOK:
                    bookId = getBookIdFromJsp(request);
                    dao.update(bookId);
                    requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                    requestDispatcher.forward(request, response);
                    break;
            }
        } catch (PersistException | IOException exception) {
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
