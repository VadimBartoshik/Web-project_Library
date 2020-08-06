package by.epam.javaweb.bartoshik.library.model.factory;


import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

/**
 * Object factory for working with a database
 */
public interface DaoFactory<Context> {

    interface DaoCreator<Context> {
        BaseDao create(Context context);
    }

    /**
     * Returns a database connection
     */
    Context getContext() throws PersistException;

    /**
     * Returns an object for managing the persistent state of the object
     */
    BaseDao getDao(Context context, Class dtoClass) throws PersistException;
}
