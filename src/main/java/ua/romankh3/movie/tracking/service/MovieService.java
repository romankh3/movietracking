package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.MovieModel;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.mapper.MovieTMDB;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;

import java.util.List;

public interface MovieService {

    MovieModel createMovieModel(final MovieEntity movieEntity);

    void markMovieAsWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException;

    void markMovieAsUnWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException;

    List<MovieTMDB> retrieveMoviesByFavoriteActors(Integer userId) throws NotFoundException;

    List<MovieTMDB> retrieveMoviesByActorsAndReleaseYear(Integer userId, Integer year) throws NotFoundException;
}
