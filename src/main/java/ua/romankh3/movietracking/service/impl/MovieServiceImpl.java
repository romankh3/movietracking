package ua.romankh3.movietracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.Movie;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.repository.MovieRepository;
import ua.romankh3.movietracking.service.MovieService;
import ua.romankh3.movietracking.service.UserService;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private UserService userService;

    @Autowired
    public MovieServiceImpl(final MovieRepository movieRepository,
                            final UserService userService) {
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void markMovieAsWatched(Integer movieId) {
        User user = userService.findAuthenticatedUser();
        movieRepository.findById(movieId).ifPresent(it -> {
            user.getMovies().add(it);
            userService.saveUser(user);
        });
    }

    @Override
    public void markMovieAsUnwatched(Integer movieId) {
        User user = userService.findAuthenticatedUser();
        movieRepository.findById(movieId).ifPresent( it -> {
            user.getMovies().remove(it);
            userService.saveUser(user);
        });
    }
}
