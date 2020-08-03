package by.epam.javaweb.bartoshik.library.controller.command.impl;

import by.epam.javaweb.bartoshik.library.controller.command.BaseCommand;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand extends BaseCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        String language = request.getParameter("language");
        request.getSession().setAttribute("language", language);
        response.sendRedirect(request.getHeader("Referer"));
    }
}
