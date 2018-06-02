package ua.romankh3.movie.tracking.service.impl;

import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.MovieModel;
import ua.romankh3.movie.tracking.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public MovieModel createMovieModel(MovieModel movieModel) {
        return null;
    }

    @Override
    public void markMovieAsViewed() {

    }

    @Override
    public void markMovieAsUnViewed() {

    }
}
