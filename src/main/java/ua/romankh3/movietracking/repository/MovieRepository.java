package ua.romankh3.movietracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movietracking.model.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findById(final Integer id);

    Optional<Movie> findByTmdbId(final Integer tmdbId);
}
