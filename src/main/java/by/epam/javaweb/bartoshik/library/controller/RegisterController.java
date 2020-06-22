package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.dao.RegisterDao;
import by.epam.javaweb.bartoshik.library.entity.RegisterBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btn_register") != null) {
            String firstName = request.getParameter("txt_firstName");
            String lastName = request.getParameter("txt_lastName");
            String email = request.getParameter("txt_email");
            String password = request.getParameter("txt_password");
            logger.info(firstName + " " + lastName + " " + email + " " + password);

            RegisterBean registerBean = new RegisterBean();

            registerBean.setFirstName(firstName);
            registerBean.setLastName(lastName);
            registerBean.setEmail(email);
            registerBean.setPassword(password);

            RegisterDao registerdao = new RegisterDao();

            String registerValidate = registerdao.authorizeRegister(registerBean);
            logger.info(registerValidate);
            if (registerValidate.equals("SUCCESS")) {

                request.setAttribute("RegisterSuccessMsg", registerValidate);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("RegisterErrorMsg", registerValidate);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                requestDispatcher.include(request, response);
            }
        }
    }
}
