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

import static by.epam.javaweb.bartoshik.library.controller.command.ServletPath.ADD_BOOK;

public class BookController extends HttpServlet {
    public static Logger logger = LogManager.getRootLogger();
    private BaseDao dao;
    CommandProvider provider = new CommandProvider();

    public void init() {
//        DaoFactory<Connection> factory = new MySqlDaoFactory();
//        try {
//            Connection connection = factory.getContext();
//            dao = factory.getDao(connection, Book.class);
//        } catch (PersistException e) {
//            e.printStackTrace();
//        }

        provider.create();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, UnsupportedEncodingException,IOException {


//        final String ADD_BOOK = "/addBook";
//        final String DELETE_BOOK = "/deleteBook";
//        final String GET_ALL_BOOK = "/getAllBook";
//        final String RETURN_BOOK = "/returnBook";
//        final String TAKE_BOOK = "/takeBook";


        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        ServletPath path = ServletPath.valueOf(action);
        Command command = provider.getCommands().get(path);

        command.execute(request, response );


        logger.info(action);
        Book book;
        Integer bookId;

//        try {
//            switch (action) {
//                case ADD_BOOK:
//                    book = getBookFromJsp(request);
//                    dao.create(book);
//                    setRequestDispatcher(request,response,"present.jsp");
//                    break;
//                case DELETE_BOOK:
//                    bookId = getBookIdFromJsp(request);
//                    logger.info(bookId);
//                    dao.delete(bookId);
//                    setRequestDispatcher(request,response,"buy.jsp");
//                    break;
//                case GET_ALL_BOOK:
//                    request.setAttribute("books", dao.getAll());
//                    setRequestDispatcher(request,response,"getAll.jsp");
//                    break;
//                case RETURN_BOOK:
//                    logger.info("RETURN_BOOK case started");
//                    bookId = getBookIdFromJsp(request);
//                    dao.update(bookId, null);
//                    setRequestDispatcher(request,response,"return.jsp");
//                    break;
//                case TAKE_BOOK:
//                    bookId = getBookIdFromJsp(request);
//                    dao.update(bookId, (String) request.getSession().getAttribute("login"));
//                    setRequestDispatcher(request,response,"take.jsp");
//                    break;
//            }
//        } catch (PersistException | IOException exception) {
//            throw new ServletException(exception);
//        }
    }

    private Book getBookFromJsp(HttpServletRequest request) {
        String title = request.getParameter("txt_title");
        String author = request.getParameter("txt_author");
        return new Book(title, author);
    }

    private Integer getBookIdFromJsp(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

//    private void setRequestDispatcher(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
//        RequestDispatcher  requestDispatcher = request.getRequestDispatcher(jspName);
//        requestDispatcher.forward(request, response);
//    }


}
