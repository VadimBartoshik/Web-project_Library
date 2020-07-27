package by.epam.javaweb.bartoshik.library.model.factory;


import by.epam.javaweb.bartoshik.library.model.dao.base.BaseDao;
import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

/**
 * Фабрика объектов для работы с базой данных
 */
public interface DaoFactory<Context> {

     interface DaoCreator<Context> {
         BaseDao create(Context context);
    }

    /**
     * Возвращает подключение к базе данных
     */
     Context getContext() throws PersistException;

    /**
     * Возвращает объект для управления персистентным состоянием объекта
     */
     BaseDao getDao(Context context, Class dtoClass) throws PersistException;
}
