package by.epam.javaweb.bartoshik.library.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
   public static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Moscow&useSSL=false";
   public static final String USER_NAME = "root";
   public static final String PASSWORD = "dflbv181818";

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}



