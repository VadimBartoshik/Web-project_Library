package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class BaseJDBCDao<T extends Identified<PK>, PK extends Integer> implements BaseDao<T, PK> {
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
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, PK key) throws PersistException;

    /**
     * Устанавливает аргументы delete запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForDelete(PreparedStatement statement, PK key) throws PersistException;

    @Override
    public List<T> getAll() throws PersistException {
        logger.info("getAll method begin");
        List<T> list;
        String sql = getSelectQuery();
        logger.info(sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.info("prepareStatement begin");
            ResultSet rs = statement.executeQuery();
            logger.info("ResultSet is update");
            list = parseResultSet(rs);
        } catch (Exception exception) {
            logger.info("Exception in getAll method");
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
    public void update(PK key) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, key);
            statement.execute(sql);
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
                statement.execute(sql);
            } catch (Exception e) {
                throw new PersistException(e);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
