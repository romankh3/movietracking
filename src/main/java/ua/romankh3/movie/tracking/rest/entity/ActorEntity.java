package ua.romankh3.movie.tracking.rest.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class ActorEntity implements Serializable, Cloneable {

    @XmlElement
    private Integer actor_id;

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }
}
