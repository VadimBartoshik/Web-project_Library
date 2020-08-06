package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.entity.User;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * Unified interface for managing persistent state of objects
 *
 * @param <T>  persistence object type
 * @param <PK> primary key type
 */
public interface BaseDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Creates a new record and its corresponding object
     */
    void create(T object) throws PersistException;

    /**
     * Stores the state of an object in the database
     */
    void update(PK key, String stringField) throws PersistException;

    /**
     * Removes an object record from the database
     */
    void delete(PK key) throws PersistException;

    /**
     * Returns a list of objects matching all records in the database
     */
    List<T> getAll() throws PersistException;

    /**
     * Returns the id of the object corresponding to its text field
     */
    public Integer getUserId(String stringField) throws PersistException;

    /**
     * Checks if the passed user is in the database
     */
    Boolean isAuthorizeLogin(User user) throws PersistException;
}
