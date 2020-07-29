package by.epam.javaweb.bartoshik.library.trash;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnController   extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("ReturnController servlet started");
        String action=request.getServletPath();
        logger.info(action);
        request.setCharacterEncoding("UTF-8");
        try {

            int bookId =Integer.parseInt(request.getParameter("id"));
            ReturnDao.returnBook(ConnectionCreator.getConnection(), bookId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("return.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}


