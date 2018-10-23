package ua.romankh3.movietracking.tmdb.service;

import ua.romankh3.movietracking.tmdb.model.MovieTMDB;

import java.util.List;

public interface MovieTmbdService {

    List<MovieTMDB> findNowPlaying();
}
