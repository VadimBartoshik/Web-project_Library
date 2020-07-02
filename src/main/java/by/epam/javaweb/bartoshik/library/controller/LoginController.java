package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.dao.LoginDao;
import by.epam.javaweb.bartoshik.library.entity.LoginBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btn_login") != null) {
            String email = request.getParameter("txt_email");
            String password = request.getParameter("txt_password");

            LoginBean loginBean = new LoginBean();

            loginBean.setEmail(email);
            loginBean.setPassword(password);

            LoginDao loginDao = new LoginDao();

            String authorize = loginDao.authorizeLogin(loginBean);
            logger.info(authorize);
            if (authorize.equals("SUCCESS LOGIN")) {
                HttpSession session = request.getSession();
                session.setAttribute("login", loginBean.getEmail());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("WrongLoginMsg", authorize);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.include(request, response);
            }
        }
    }
}