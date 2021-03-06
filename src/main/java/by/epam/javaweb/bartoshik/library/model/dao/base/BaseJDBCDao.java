package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Abstract class providing basic implementation of CRUD operations using JDBC.
 *
 * @param <T>  persistence object type
 * @param <PK> primary key type
 */
public abstract class BaseJDBCDao<T extends Identified<PK>, PK extends Integer> implements BaseDao<T, PK> {
    private static final String AUTHORIZE_LOGIN = "select * from user where email=? and password=?";
    private DaoFactory<Connection> parentFactory;
    private Connection connection;
    public static Logger logger = LogManager.getRootLogger();

    public BaseJDBCDao(DaoFactory<Connection> parentFactory, Connection connection) {
        this.parentFactory = parentFactory;
        this.connection = connection;
    }

    /**
     * Returns a sql query to get all records.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Returns a sql query to insert a new record into the database.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Returns a sql query to update a record.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Returns a sql query to check authorization from the database.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Parses the ResultSet and returns a list of objects matching the contents of the ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Sets the insert arguments of the query to match the field values of the object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Sets the update arguments of the request to match the values of the key and secondKey fields.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, String key, PK secondKey) throws PersistException;

    /**
     * Sets the delete arguments of the request to match the field values of the key object.
     */
    protected abstract void prepareStatementForDelete(PreparedStatement statement, PK key) throws PersistException;


    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception exception) {
            throw new PersistException(exception);
        }
        return list;
    }

    @Override
    public void create(T object) throws PersistException {
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }

    @Override
    public void update(PK key, String email) throws PersistException {

        String sql = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            if (email != null) {
                prepareStatementForUpdate(statement, getUserId(email).toString(), key);
            } else {

                prepareStatementForUpdate(statement, email, key);
            }
            statement.execute();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(PK key) throws PersistException {

        String sql = getDeleteQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try {
                prepareStatementForDelete(statement, key);
                statement.executeUpdate();
            } catch (SQLException e) {

                throw new PersistException(e);
            }
        } catch (SQLException e) {

            throw new PersistException(e);
        }
    }

    public Integer getUserId(String email) throws PersistException {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM USER WHERE email='" + email + "';");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Boolean isAuthorizeLogin(User user) throws PersistException {
        String sql = AUTHORIZE_LOGIN;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForAuthorizeLogin(statement, user.getEmail(), user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (user.getEmail().equals(resultSet.getString("email")) && user.getPassword().equals(resultSet.getString("password"))) ;
                {
                    return true;
                }
            }

        } catch (SQLException ex) {
            throw new PersistException(ex);
        }
        return false;
    }

    protected void prepareStatementForAuthorizeLogin(PreparedStatement statement, String email, String password) throws PersistException {
        try {
            statement.setString(1, email);
            statement.setString(2, password);
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }
}
