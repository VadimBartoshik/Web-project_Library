package by.epam.javaweb.bartoshik.library.trash;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TakeController extends HttpServlet {

    public static Logger logger = LogManager.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("TakeBookController servlet started");
        String action=request.getServletPath();
        logger.info(action);

            request.setCharacterEncoding("UTF-8");
            try {
                HttpSession session = request.getSession();
                String userEmail = (String) session.getAttribute("login");
                int bookId = Integer.parseInt(request.getParameter("id"));
                TakeDao.takeBook(ConnectionCreator.getConnection(), bookId, userEmail);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception ex) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("take.jsp");
                requestDispatcher.forward(request, response);
            }
    }


}
