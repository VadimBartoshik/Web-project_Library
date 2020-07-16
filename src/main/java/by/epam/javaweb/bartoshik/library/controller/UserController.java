package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.User;
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

public class UserController extends HttpServlet {
    private static final  String ADD_USER = "/addUser";
    private static final String DELETE_USER = "/deleteUser";
    private static final String GET_ALL_USER = "/getAllUser";
    private static final String UPDATE_USER = "/updateUser";

    private BaseDao dao;
    public static Logger logger = LogManager.getRootLogger();

    public void init() {
        DaoFactory<Connection> factory = new MySqlDaoFactory();
        try {
            Connection connection = factory.getContext();
            dao = factory.getDao(connection, User.class);
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
                case ADD_USER:
                    dao.create();
                    break;
                case DELETE_USER:
                    dao.delete();
                    //    insertBook(request, response);
                    break;
                case GET_ALL_USER:
                    dao.getAll();
                    break;
                case UPDATE_USER:
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
