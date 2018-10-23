package ua.romankh3.movietracking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Integer id;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_user", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;
}
