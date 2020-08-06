package by.epam.javaweb.bartoshik.library.model.factory;

import by.epam.javaweb.bartoshik.library.model.dao.MySqlBookDao;
import by.epam.javaweb.bartoshik.library.model.dao.MySqlUserDao;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        try {
            Properties properties = new Properties ();
            InputStream input = MySqlDaoFactory.class.getResourceAsStream("/database.properties");
            properties.load(input);
            String driverName= (String) properties.get ("driverName");
            String url= (String) properties.get ("url");
            String userName= (String) properties.get ("userName");
            String password= (String) properties.get ("password");
            Class.forName(driverName);
            return DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public BaseDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        creators = new HashMap<Class, DaoCreator>();
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public BaseDao create(Connection connection) {
                return new MySqlUserDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Book.class, new DaoCreator<Connection>() {
            @Override
            public BaseDao create(Connection connection) {
                return new MySqlBookDao(MySqlDaoFactory.this, connection);
            }
        });
    }
}
