package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.GenericDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BookController extends HttpServlet {
    private final String ADD_USER_BOOK = "/book/add";
    private final String DELETE_USER_BOOK = "/book/delete";
    private final String GET_ALL_USER_BOOK = "/book/getAll";
    private final String UPDATE_USER_BOOK = "/book/update";

    public static Logger logger = LogManager.getRootLogger();
    private GenericDao dao;


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

        try {
            switch (action) {
                case ADD_USER_BOOK:
                    dao.create();
                    break;
                case DELETE_USER_BOOK:
                    dao.delete();
                    break;
                case GET_ALL_USER_BOOK:
                    dao.getAll();
                    break;
                case UPDATE_USER_BOOK:
                    dao.update();
                    break;

                default:
                    dao.getAll();
                    break;
            }
        } catch (SQLException | PersistException ex) {
            throw new ServletException(ex);
        }
    }
}
