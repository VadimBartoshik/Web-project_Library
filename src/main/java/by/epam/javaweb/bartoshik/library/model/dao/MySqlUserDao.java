package by.epam.javaweb.bartoshik.library.model.dao;

import by.epam.javaweb.bartoshik.library.model.factory.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.AbstractJDBCDao;
import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {
    private class PersistGroup extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT id, number, department FROM daotalk.Group";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO daotalk.Group (number, department) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE daotalk.Group SET number= ?, department = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM daotalk.Group WHERE id= ?;";
    }

    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    public MySqlUserDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistGroup group = new PersistGroup();
                group.setId(rs.getInt("id"));
                group.setNumber(rs.getInt("number"));
                group.setDepartment(rs.getString("department"));
                result.add(group);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setInt(1, user.getNumber());
            statement.setString(2, user.getDepartment());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setInt(1, user.getNumber());
            statement.setString(2, user.getDepartment());
            statement.setInt(3, user.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
