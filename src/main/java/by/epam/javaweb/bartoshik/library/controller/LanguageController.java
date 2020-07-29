package by.epam.javaweb.bartoshik.library.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String language = req.getParameter("language");
        req.getSession().setAttribute("language", language);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String language = req.getParameter("language");
        req.getSession().setAttribute("language", language);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
