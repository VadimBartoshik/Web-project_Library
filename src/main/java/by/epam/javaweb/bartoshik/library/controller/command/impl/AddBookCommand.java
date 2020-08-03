package by.epam.javaweb.bartoshik.library.controller.command.impl;

import by.epam.javaweb.bartoshik.library.controller.command.BaseCommand;
import by.epam.javaweb.bartoshik.library.controller.command.Command;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookCommand extends BaseCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        init(Book.class);
        Book book = getBookFromJsp(request);
        dao.create(book);
        setRequestDispatcher(request, response, "present.jsp");
    }

    private Book getBookFromJsp(HttpServletRequest request) {
        String title = request.getParameter("txt_title");
        String author = request.getParameter("txt_author");
        return new Book(title, author);
    }

}
