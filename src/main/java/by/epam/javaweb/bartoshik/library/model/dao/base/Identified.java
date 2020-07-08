package by.epam.javaweb.bartoshik.library.model.dao.base;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    public PK getId();
}
