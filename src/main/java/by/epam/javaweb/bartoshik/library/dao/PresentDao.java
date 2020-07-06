package by.epam.javaweb.bartoshik.library.dao;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.entity.PresentBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PresentDao {
    public static Logger logger = LogManager.getRootLogger();

    public String checkInsert(PresentBean presentBean) {
        String title = presentBean.getTitle();
        String author = presentBean.getAuthor();
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
