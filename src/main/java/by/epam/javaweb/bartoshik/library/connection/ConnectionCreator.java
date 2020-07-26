package by.epam.javaweb.bartoshik.library.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {

        Properties properties = new Properties ();
        InputStream input = ConnectionCreator.class.getResourceAsStream("/database.properties");
            properties.load(input);
            String driverName= (String) properties.get ("driverName");
            String url= (String) properties.get ("url");
            String userName= (String) properties.get ("userName");
            String password= (String) properties.get ("password");
            Class.forName(driverName);

        return DriverManager.getConnection(url, userName, password);
    }
}



