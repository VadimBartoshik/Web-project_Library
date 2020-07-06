package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.connection.Runner;
import by.epam.javaweb.bartoshik.library.dao.BuyDao;
import by.epam.javaweb.bartoshik.library.entity.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class BuyController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();


@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("BuyController servlet started");
        request.setCharacterEncoding("UTF-8");
            try {
                logger.info("begin method");
                int bookId =Integer.parseInt(request.getParameter("id"));
                logger.info(bookId);
                BuyDao.buyBook(ConnectionCreator.getConnection(), bookId);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception ex) {
                logger.info("Error BuyController servlet");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("buy.jsp");
                requestDispatcher.forward(request, response);
            }
    }
}
