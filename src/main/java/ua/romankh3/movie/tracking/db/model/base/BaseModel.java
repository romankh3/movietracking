package ua.romankh3.movie.tracking.db.model.base;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Simple JavaBean domain object with an interfaces and serialVersionUID property.
 * Used as a base class for objects needing this property.
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
}
