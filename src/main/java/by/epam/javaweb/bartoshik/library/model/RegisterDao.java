package by.epam.javaweb.bartoshik.library.model;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.model.entity.RegisterBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    public static Logger logger = LogManager.getRootLogger();

    public String authorizeRegister(RegisterBean registerBean) {
        String firstName = registerBean.getFirstName();
        String lastName = registerBean.getLastName();
        String email = registerBean.getEmail();
        String password = registerBean.getPassword();

        try {
            Connection connection = ConnectionCreator.getConnection();
            logger.info("connection is good");
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into user(firstName, lastName, email, password) values (?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();
            logger.info("preparedStatement is executeUpdate");
            preparedStatement.close();
            connection.close();

            return "SUCCESS";
        } catch (IOException e) {
            logger.info("IOException");
            e.printStackTrace();
        } catch (SQLException throwable) {
            logger.info("SQLException");
            throwable.printStackTrace();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "FAIL";
    }
}
