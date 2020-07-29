package by.epam.javaweb.bartoshik.library.controller.command;

import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public abstract class BaseCommand implements Command {
    protected BaseDao dao;

    public void init() {
        DaoFactory<Connection> factory = new MySqlDaoFactory();
        try {
            Connection connection = factory.getContext();
            dao = factory.getDao(connection, Book.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    protected void setRequestDispatcher(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspName);
        requestDispatcher.forward(request, response);
    }
}
