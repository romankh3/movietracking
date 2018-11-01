package ua.romankh3.movietracking.service;

import ua.romankh3.movietracking.model.Movie;

public interface MovieService {

    Movie createMovie(Integer thmbMovieId);

    void markMovieAsWatched(Integer movieId);
    void markMovieAsUnwatched(Integer movieId);
}
