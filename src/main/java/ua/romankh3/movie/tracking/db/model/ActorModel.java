package ua.romankh3.movie.tracking.db.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.romankh3.movie.tracking.db.model.base.IdModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean object for a Actor table.
 */
@Entity
@Table(name = "Actor")
public class ActorModel extends IdModel {

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    @Override
    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }

    @Override
    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }

    @Override
    public String toString() { return ToStringBuilder.reflectionToString(this); }

}
