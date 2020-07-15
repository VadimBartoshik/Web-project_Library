package by.epam.javaweb.bartoshik.library.model.dao;

import by.epam.javaweb.bartoshik.library.model.DaoFactory;
import by.epam.javaweb.bartoshik.library.model.dao.base.AbstractJDBCDao;
import by.epam.javaweb.bartoshik.library.model.entity.Book;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlBookDao extends AbstractJDBCDao<Book, Integer>


{

    private class PersistBook extends Book {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title, author FROM book WHERE userId is null;";
        //  return "SELECT id, name, surname, enrolment_date, group_id FROM daotalk.Student ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO daotalk.Student (name, surname, enrolment_date, group_id) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE daotalk.Student \n" +
                "SET name = ?, surname  = ?, enrolment_date = ?, group_id = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM daotalk.Student WHERE id= ?;";
    }

    @Override
    public Book create() throws PersistException {
        Book book = new Book();
        return persist(book);
    }

    public MySqlBookDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Student.class, "group");
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
        List<Book> result = new LinkedList<Book>();
        try {
            while (rs.next()) {
                PersistStudent student = new PersistStudent();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setEnrolmentDate(rs.getDate("enrolment_date"));
                student.setGroup((Group) getDependence(Group.class, rs.getInt("group_id")));
                result.add(student);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Student object) throws PersistException {
        try {
            Date sqlDate = convert(object.getEnrolmentDate());
            int groupId = (object.getGroup() == null || object.getGroup().getId() == null) ? -1
                    : object.getGroup().getId();
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setDate(3, sqlDate);
            statement.setInt(4, groupId);
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Student object) throws PersistException {
        try {
            Date sqlDate = convert(object.getEnrolmentDate());
            int groupId = (object.getGroup() == null || object.getGroup().getId() == null) ? -1
                    : object.getGroup().getId();
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setDate(3, sqlDate);
            statement.setInt(4, groupId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}
