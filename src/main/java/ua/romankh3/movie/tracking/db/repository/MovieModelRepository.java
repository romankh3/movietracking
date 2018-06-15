package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movie.tracking.db.model.MovieModel;

import java.util.List;
import java.util.Optional;

public interface MovieModelRepository extends JpaRepository<MovieModel, Integer> {

    List<MovieModel> findAll();

    Optional<MovieModel> findById(final Integer id);

    Optional<MovieModel> findByTmdbId(final Integer tmdbId);

    MovieModel save(final MovieModel movieModel);
}
