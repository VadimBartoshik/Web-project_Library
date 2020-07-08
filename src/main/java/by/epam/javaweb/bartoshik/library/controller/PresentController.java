package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.model.PresentDao;
import by.epam.javaweb.bartoshik.library.model.entity.PresentBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PresentController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("PresentController method started");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("btn_present") != null) //check button click event not null from add.jsp page after continue
        {
            String title = request.getParameter("txt_title");
            String author = request.getParameter("txt_author");
            logger.info(title);
            logger.info(title);
            PresentBean presentBean = new PresentBean();

            presentBean.setTitle(title);
            presentBean.setAuthor(author);

            PresentDao presentDao = new PresentDao();

            String insertValidate = presentDao.checkInsert(presentBean);
            logger.info(insertValidate);
            if (insertValidate.equals("INSERT SUCCESS")) //check calling checkInsert() function receive string "INSERT SUCCESS" after redirect to index.jsp page and display record
            {
                request.setAttribute("InsertSuccessMsg", insertValidate); //setAttribute value is "InsertSuccessMsg" for insert successfully message
                RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("InsertErrorMsg", insertValidate); //setAttribute value is "InsertErrorMsg" for insert fail message
                RequestDispatcher rd = request.getRequestDispatcher("present.jsp");
                rd.include(request, response);
            }
        }
    }
}
