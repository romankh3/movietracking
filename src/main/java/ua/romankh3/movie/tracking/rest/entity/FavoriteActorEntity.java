package ua.romankh3.movie.tracking.rest.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class FavoriteActorEntity  extends ActorEntity {

    @XmlElement
    @NotNull
    private Integer user_id;

    @Override
    public Integer getUser_id() {
        return user_id;
    }

    @Override
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
