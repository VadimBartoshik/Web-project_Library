package by.epam.javaweb.bartoshik.library.model.factory;

import by.epam.javaweb.bartoshik.library.model.dao.MySqlBookDao;
import by.epam.javaweb.bartoshik.library.model.dao.MySqlUserDao;
import by.epam.javaweb.bartoshik.library.model.dao.base.GenericDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    private final String USER = "root";
    private final String PASSWORD = "dflbv181818";
    private final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Moscow&useSSL=false";
    private final String DRIVER = "com.mysql.jdbc.Driver";  //com.mysql.cj.jdbc.Driver
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(DRIVER);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Book.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlBookDao(MySqlDaoFactory.this, connection);
            }
        });
    }
}
