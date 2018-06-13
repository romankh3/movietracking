package ua.romankh3.movie.tracking.db.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean object adds a firstName and lastName properties to {@link BaseModel}.
 * Used as a bse class for object needing these properties.
 */
@MappedSuperclass
public abstract class NameModel extends IdModel {

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "first_name" )
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
