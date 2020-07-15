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


public class BuyController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();


@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("BuyController servlet started");
        request.setCharacterEncoding("UTF-8");
            try {
                HttpSession session = request.getSession();
                String s=(String) session.getAttribute("login");
                logger.info("sessionID="+s);
                logger.info("begin method");
                int bookId =Integer.parseInt(request.getParameter("id"));
                logger.info(bookId);
                BuyDao.buyBook(ConnectionCreator.getConnection(), bookId);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception ex) {
                logger.info("Error BuyController servlet");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("getAll.jsp");
                requestDispatcher.forward(request, response);
            }
    }
}
