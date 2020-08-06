package by.epam.javaweb.bartoshik.library.controller.command;

import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, PersistException;

}
