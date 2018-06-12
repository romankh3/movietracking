package ua.romankh3.movie.tracking.service;

import java.io.IOException;
import java.util.List;

public interface TmdbAPIService {

    String retrieveMovies(String path) throws IOException;

    String retrieveMovies(String path, String primaryReleaseYear);

    String retrieveMovies(String path, String primaryReleaseYear, String primaryReleaseMonth);

    String retrieveMovies(String path, List<Integer> favoriteActorIds);

    String retrieveMovies(String path, String primaryReleaseYear, List<Integer> favoriteActorIds);

    String retrieveMovies(String path,
                          String primaryReleaseYear,
                          String primaryReleaseMonth,
                          List<Integer> favoriteActorIds);
}
