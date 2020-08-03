package by.epam.javaweb.bartoshik.library.controller.command.impl;

import by.epam.javaweb.bartoshik.library.controller.command.BaseCommand;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand extends BaseCommand {
    public static Logger logger = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        init(User.class);
        User user = getUserFromJsp(request);
        dao.create(user);
        setRequestDispatcher(request, response, "register.jsp");
    }

    private User getUserFromJsp(HttpServletRequest request) {
        String firstName = request.getParameter("txt_firstName");
        String lastName = request.getParameter("txt_lastName");
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        return new User(firstName, lastName, email, password);
    }


}
