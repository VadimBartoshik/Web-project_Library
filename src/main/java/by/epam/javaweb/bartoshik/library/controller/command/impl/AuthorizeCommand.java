package by.epam.javaweb.bartoshik.library.controller.command.impl;

import by.epam.javaweb.bartoshik.library.controller.command.BaseCommand;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizeCommand  extends BaseCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        init(Book.class);
        User user = getUserFromJsp(request);

        if (dao.isAuthorizeLogin(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", user.getEmail());
            setRequestDispatcher(request, response, "welcome.jsp");
        } else {
//            request.setAttribute("WrongLoginMsg", dao.isAuthorizeLogin(user));
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            requestDispatcher.include(request, response);
            setRequestDispatcher(request, response, "index.jsp");
        }

    }

    private User getUserFromJsp(HttpServletRequest request) {
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        return new User(email, password);
    }
}
