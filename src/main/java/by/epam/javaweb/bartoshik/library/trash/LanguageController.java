package by.epam.javaweb.bartoshik.library.trash;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //   ServletContext servletContext = getServletContext();
        String language = req.getParameter("language");
        req.getSession().setAttribute("language", language);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     //   ServletContext servletContext = getServletContext();
        String language = req.getParameter("language");
        req.getSession().setAttribute("language", language);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
