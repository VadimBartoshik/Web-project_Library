package by.epam.javaweb.bartoshik.library.model.dao;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.BaseJDBCDao;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends BaseJDBCDao<User, Integer> {

    public static final String GET_SELECT_QUERY = "SELECT firstName, lastName, email FROM user;";
    public static final String GET_CREATE_QUERY = "INSERT INTO user (firstName, lastName, email, password) VALUES (?, ?, ?, ?);";
    public static final String GET_UPDATE_QUERY = "UPDATE daotalk.Group SET number= ?, department = ? WHERE id= ?;";
    public static final String GET_DELETE_QUERY = "DELETE FROM user WHERE id= ?;";

    public MySqlUserDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    public String getSelectQuery() {
        return GET_SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return GET_CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return GET_UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return GET_DELETE_QUERY;
    }



    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirsName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                result.add(user);
            }
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setString(1, user.getFirsName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, String key, Integer secondKey) throws PersistException {

    }


    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Integer key) throws PersistException {
        try {
            statement.setInt(1, key);
        } catch (SQLException exception) {
            throw new PersistException(exception);
        }
    }




}
