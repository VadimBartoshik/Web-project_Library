package by.epam.javaweb.bartoshik.library.dao;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.entity.AddBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddDao {
    public static Logger logger = LogManager.getRootLogger();

    public String checkInsert(AddBean addBean) {
        String title = addBean.getTitle();
        String author = addBean.getAuthor();
        logger.info(title);
        logger.info(author);
        try {
            Connection connection = ConnectionCreator.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (title, author) VALUES (?,?);");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.executeUpdate();  //execute query

            preparedStatement.close(); //close statement

            connection.close(); //close connection

            return "INSERT SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL INSERT";
    }

}
