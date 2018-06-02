package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.MovieModel;

public interface MovieService {

    MovieModel createMovieModel(MovieModel movieModel);

    void markMovieAsViewed();

    void markMovieAsUnViewed();
}
