package ua.romankh3.movietracking.tmdb.service;

import ua.romankh3.movietracking.tmdb.model.ActorCastTMDB;
import ua.romankh3.movietracking.tmdb.model.MovieTMDB;

import java.io.IOException;
import java.util.List;

public interface TmdbDiscoverService {

    List<MovieTMDB> discoverMovies(Integer primaryReleaseYear);

    List<MovieTMDB> discoverMovies(List<Integer> favoriteActorIds);

    List<MovieTMDB> discoverMovies(Integer primaryReleaseYear, List<Integer> favoriteActorIds);

    List<MovieTMDB> discoverMovies(Integer primaryReleaseYear,
                                   Integer month,
                                   List<Integer> favoriteActorIds);
}
