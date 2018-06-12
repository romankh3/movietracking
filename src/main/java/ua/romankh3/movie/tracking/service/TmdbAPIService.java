package ua.romankh3.movie.tracking.service;

import java.io.IOException;

public interface TmdbAPIService {

    String retrieveMovies(String path) throws IOException;
}
