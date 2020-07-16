package by.epam.javaweb.bartoshik.library.model.dao.base;

import by.epam.javaweb.bartoshik.library.model.exeption.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface BaseDao<T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись и соответствующий ей объект */
    public void create(T object) throws PersistException;

    /** Создает новую запись, соответствующую объекту object */
//    public T persist(T object)  throws PersistException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getByPK(PK key) throws PersistException;

    /** Сохраняет состояние объекта в базе данных */
    public void update(PK key) throws PersistException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(PK key) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws PersistException;
}