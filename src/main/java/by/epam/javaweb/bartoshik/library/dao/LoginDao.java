package by.epam.javaweb.bartoshik.library.dao;

import by.epam.javaweb.bartoshik.library.connection.ConnectionCreator;
import by.epam.javaweb.bartoshik.library.entity.LoginBean;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public String authorizeLogin(LoginBean loginBean) {
        String bdEmail = "";
        String bdPassword = "";
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        try {
            Connection connection = ConnectionCreator.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bdEmail = resultSet.getString("email");
                bdPassword = resultSet.getString("password");
                if(email.equals(bdEmail) && password.equals(bdPassword));{
                    return "SUCCESS LOGIN";
                }
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "WRONG USERNAME AND PASSWORD";
    }
}
