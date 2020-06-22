package by.epam.javaweb.bartoshik.library.connection;

import java.io.IOException;
import java.sql.*;

import static by.epam.javaweb.bartoshik.library.connection.ConnectionCreator.getConnection;

public class Runner {
    public static void main(String[] args) {
        try {

                printAllTable(ConnectionCreator.getConnection());
                System.out.println("Connection to Store DB success full!");

        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void printAllTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

}
