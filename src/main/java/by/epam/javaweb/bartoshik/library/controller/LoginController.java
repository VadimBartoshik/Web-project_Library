package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginController extends HttpServlet {
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
            throws ServletException, IOException {
        try {
            User user = getUserFromJsp(request);

            String authorize = null;

            authorize = dao.authorizeLogin(user);


            if (authorize.equals("SUCCESS LOGIN")) {
                HttpSession session = request.getSession();
                session.setAttribute("login", user.getEmail());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("WrongLoginMsg", authorize);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.include(request, response);
            }
        }
         catch (PersistException | IOException exception) {
            throw new ServletException(exception);
        }
    }


    private User getUserFromJsp(HttpServletRequest request) {
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        return new User(email, password);
    }
}