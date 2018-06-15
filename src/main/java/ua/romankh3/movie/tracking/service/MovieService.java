package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.MovieModel;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;

public interface MovieService {

    MovieModel createMovieModel(final MovieEntity movieEntity);

    void markMovieAsWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException;

    void markMovieAsUnWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException;

    String retrieveMoviesByFavoriteActors(Integer userId) throws NotFoundException;
}
