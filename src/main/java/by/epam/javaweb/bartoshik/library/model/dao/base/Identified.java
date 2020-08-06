package by.epam.javaweb.bartoshik.library.model.dao.base;

import java.io.Serializable;

/**
 * Identifiable object interface.
 */
public interface Identified<PK extends Serializable> {

    /**
     * Returns the identifier of the object
     */
    PK getId();
}
