package ua.romankh3.movie.tracking.service;

import java.io.IOException;
import java.util.List;

public interface TmdbAPIService {

    String retrieveMovies() throws IOException;

    String retrieveMovies(String primaryReleaseYear);

    String retrieveMovies(List<Integer> favoriteActorIds);

    String retrieveMovies(String primaryReleaseYear, List<Integer> favoriteActorIds);
}
