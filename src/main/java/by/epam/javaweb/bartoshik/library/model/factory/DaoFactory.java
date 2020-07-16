package by.epam.javaweb.bartoshik.library.model.factory;


import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

/**
 * Фабрика объектов для работы с базой данных
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public BaseDao create(Context context);
    }

    /**
     * Возвращает подключение к базе данных
     */
    public Context getContext() throws PersistException;

    /**
     * Возвращает объект для управления персистентным состоянием объекта
     */
    public BaseDao getDao(Context context, Class dtoClass) throws PersistException;
}
