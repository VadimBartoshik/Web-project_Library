package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.entity.Book;
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
import java.sql.Connection;

public class UserController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    private BaseDao<User, Integer> dao;

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
            throws ServletException {

        final String ADD_USER = "/addUser";
        final String DELETE_USER = "/deleteUser";
        final String GET_ALL_USER = "/getAllUser";
        final String UPDATE_USER = "/updateUser";

        String action = request.getServletPath();
        User user;
        Integer userId;

        try {
            switch (action) {
                case ADD_USER:
                    user = getUserFromJsp(request);
                    dao.create(user);
                    break;
                case DELETE_USER:
                    userId = getUserIdFromJsp(request);
                    dao.delete(userId);
                    break;
                case GET_ALL_USER:
                    dao.getAll();
                    break;
                case UPDATE_USER:
                    userId = getUserIdFromJsp(request);
                    dao.update(userId);
                    break;
            }
        } catch (PersistException ex) {
            throw new ServletException(ex);
        }
    }

    private User getUserFromJsp(HttpServletRequest request) {
        String firstName = request.getParameter("txt_firstName");
        String lastName = request.getParameter("txt_lastName");
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        return new User(firstName, lastName, email, password);
    }

    private Integer getUserIdFromJsp(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
