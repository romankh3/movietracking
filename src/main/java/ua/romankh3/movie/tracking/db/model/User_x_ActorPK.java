package ua.romankh3.movie.tracking.db.model;

import java.io.Serializable;

/**
 * Primary Key for User_x_Actor table.
 */
public class User_x_ActorPK implements Serializable {

    private Integer user_id;

    private Integer actor_id;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }
}
