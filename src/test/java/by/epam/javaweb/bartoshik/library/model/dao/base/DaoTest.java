package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.factory.MySqlDaoFactory;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoTest extends TestCase {
    private static final DaoFactory<Connection> factory = new MySqlDaoFactory();

    private Connection connection;

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = factory.getContext();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    public void testGetAll() {

    }

    public void testCreate() throws PersistException {

        Book testBook = new Book("The Adventures of Tom Sawyer", "Mark Twain");
        factory.getDao(connection, Book.class).create(testBook);
        Assert.assertNull("Group is not null.", student.getGroup());


        User user = new User("bartoshik@gmail.com", "qwerty12");
        factory.getDao(connection, User.class).create(user);



        Group group = new Group();
        student.setGroup(group);
        Assert.assertNotNull("Group is null.", student.getGroup());
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testGetUserId() {
    }

    public void testAuthorizeLogin() {
    }
}