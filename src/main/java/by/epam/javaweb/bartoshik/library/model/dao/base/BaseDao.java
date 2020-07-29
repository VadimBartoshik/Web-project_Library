package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface BaseDao<T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись и соответствующий ей объект */
    public void create(T object) throws PersistException;

    /** Сохраняет состояние объекта в базе данных */
    public void update(PK key, String stringField) throws PersistException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(PK key) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws PersistException;

    /** Возвращает id объекта соответствующего его текстовому полю*/
    public Integer getUserId(String stringField) throws PersistException;
}
