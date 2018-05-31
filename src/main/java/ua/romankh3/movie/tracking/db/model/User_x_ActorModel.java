package ua.romankh3.movie.tracking.db.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "User_x_Actor")
public class User_x_ActorModel {

    @EmbeddedId
    private User_x_ActorPK id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", insertable = false, updatable = false)
    private ActorModel actorModel;

    @Column(name = "favorite")
    private Boolean favorite;

    @Override
    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }

    @Override
    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }

    @Override
    public String toString() { return ToStringBuilder.reflectionToString(this); }
}
