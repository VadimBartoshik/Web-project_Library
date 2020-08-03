package by.epam.javaweb.bartoshik.library.controller.command.impl;

import by.epam.javaweb.bartoshik.library.controller.command.BaseCommand;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnBookCommand extends BaseCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        init(Book.class);
        Integer bookId = getBookIdFromJsp(request);
        dao.update(bookId, null);
        setRequestDispatcher(request, response, "return.jsp");
    }

    private Integer getBookIdFromJsp(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
