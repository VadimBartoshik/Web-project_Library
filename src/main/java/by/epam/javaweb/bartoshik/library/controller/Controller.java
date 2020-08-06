package by.epam.javaweb.bartoshik.library.controller;

import by.epam.javaweb.bartoshik.library.controller.command.Command;
import by.epam.javaweb.bartoshik.library.controller.command.CommandProvider;
import by.epam.javaweb.bartoshik.library.controller.command.ServletPath;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
