package ua.romankh3.movie.tracking.db.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.romankh3.movie.tracking.db.model.base.BaseModel;

import javax.persistence.*;

/**
 * Simple JavaBean object for a User_x_Movie table.
 */
@Entity
@Table(name = "User_x_Movie")
public class User_x_MovieModel extends BaseModel {

    @EmbeddedId
    private User_x_MoviePK id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private MovieModel movieModel;

    @Column(name = "watched")
    private Boolean watched;

    public User_x_MoviePK getId() {
        return id;
    }

    public void setId(User_x_MoviePK id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    @Override
    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }

    @Override
    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }

    @Override
    public String toString() { return ToStringBuilder.reflectionToString(this); }
}
