package ua.romankh3.movie.tracking.rest.entity;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class ActorEntity implements Serializable, Cloneable {

    @XmlElement
    private Integer actor_id;

    @XmlElement
    private String firstName;

    @XmlElement
    private String lastName;

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
