package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.controller.command.Command;
import by.epam.javaweb.bartoshik.library.controller.command.CommandProvider;
import by.epam.javaweb.bartoshik.library.controller.command.ServletPath;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import static by.epam.javaweb.bartoshik.library.controller.command.ServletPath.*;
import static by.epam.javaweb.bartoshik.library.controller.command.ServletPath.ADD_BOOK;

public class Controller extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    CommandProvider provider = new CommandProvider();

    public void init() {
        provider.create();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        logger.info(action);
        ServletPath path = ServletPath.getServletPathByName(action);
        logger.info(path);
        Command command = provider.getCommands().get(path);
        logger.info(command.toString());

        try {
            command.execute(request, response);
        } catch (PersistException e) {
            e.printStackTrace();
        }

    }

}
