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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController extends HttpServlet {
    private final String ADD_USER_BOOK = "/addBook";
    private final String DELETE_USER_BOOK = "/deleteBook";
    private final String GET_ALL_USER_BOOK = "/getAllBook";
    private final String UPDATE_USER_BOOK = "/updateBook";

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
            throws ServletException, IOException {
        String action = request.getServletPath();
        Integer bookId = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("txt_title");
        String author = request.getParameter("txt_author");
        Book book=new Book(title,author);

        try {
            switch (action) {
                case ADD_USER_BOOK:
                    dao.create(book);
                    break;
                case DELETE_USER_BOOK:
                    dao.delete(bookId);
                    break;
                case GET_ALL_USER_BOOK:
                    dao.getAll();
                    break;
                case UPDATE_USER_BOOK:
                    dao.update(bookId);
                    break;
            }
        } catch (PersistException persistException) {
            throw new ServletException(persistException);
        }
    }

    private String getUserEmail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("login");
        //  int bookId =Integer.parseInt(request.getParameter("id"));
    }

    public static int getUserId(Connection connection, String email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM USER WHERE email='" + email + "';");
        resultSet.next();
        return resultSet.getInt(1);
    }
}
