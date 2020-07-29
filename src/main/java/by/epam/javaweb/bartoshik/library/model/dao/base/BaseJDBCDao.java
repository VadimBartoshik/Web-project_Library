package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class BaseJDBCDao<T extends Identified<PK>, PK extends Integer> implements BaseDao<T, PK> {
    private static final String AUTHORIZE_LOGIN = "select * from user where email=? and password=?";
    private static final String SUCCESS = "SUCCESS LOGIN";
    private static final String WRONG = "WRONG USERNAME AND PASSWORD";
    private DaoFactory<Connection> parentFactory;
    private Connection connection;
    public static Logger logger = LogManager.getRootLogger();

    public BaseJDBCDao(DaoFactory<Connection> parentFactory, Connection connection) {
        this.parentFactory = parentFactory;
        this.connection = connection;
    }

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей key and secondKey.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, String key, PK secondKey) throws PersistException;

    /**
     * Устанавливает аргументы delete запроса в соответствии со значением полей объекта key.
     */
    protected abstract void prepareStatementForDelete(PreparedStatement statement, PK key) throws PersistException;

//    /**
//     * Устанавливает аргументы AuthorizeLogin запроса в соответствии со значением полей email and password.
//     */
//    protected abstract void prepareStatementForAuthorizeLogin(PreparedStatement statement, String email, String password) throws PersistException;


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
        logger.info("update method started");
        String sql = getUpdateQuery();
        logger.info(sql);
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            logger.info("key is -" + key);
            if (email != null) {
                prepareStatementForUpdate(statement, getUserId(email).toString(), key);
            } else {
                logger.info(email);
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
    public String authorizeLogin(User user) throws PersistException {
        String bdEmail = "";
        String bdPassword = "";
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = AUTHORIZE_LOGIN;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForAuthorizeLogin(statement, email, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bdEmail = resultSet.getString("email");
                bdPassword = resultSet.getString("password");
                if (email.equals(bdEmail) && password.equals(bdPassword)) ;
                {
                    return SUCCESS;
                }
            }

        } catch (SQLException | PersistException ex) {
            throw new PersistException(ex);
        }
        return WRONG;
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
