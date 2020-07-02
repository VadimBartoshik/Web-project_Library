package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.dao.AddDao;
import by.epam.javaweb.bartoshik.library.entity.AddBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btn_add") != null) //check button click event not null from add.jsp page after continue
        {
            String title = request.getParameter("txt_title");
            String author = request.getParameter("txt_author");

            AddBean addBean = new AddBean();

            addBean.setTitle(title);
            addBean.setAuthor(author);

            AddDao addDao = new AddDao();

            String insertValidate = addDao.checkInsert(addBean);

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
