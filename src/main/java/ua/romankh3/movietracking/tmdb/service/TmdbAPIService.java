package ua.romankh3.movietracking.tmdb.service;

import ua.romankh3.movietracking.tmdb.model.MovieTMDB;

import java.io.IOException;
import java.util.List;

public interface TmdbAPIService {

    List<MovieTMDB> retrieveMovies() throws IOException;

    List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear);

    List<MovieTMDB> retrieveMovies(List<Integer> favoriteActorIds);

    List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear, List<Integer> favoriteActorIds);

    List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear,
                                   Integer month,
                                   List<Integer> favoriteActorIds);
}
