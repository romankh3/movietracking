package ua.romankh3.movie.tracking.rest.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class ViewedMovieEntity extends MovieEntity {

    @NotNull
    @XmlElement
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
