package ua.romankh3.movietracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.model.Movie;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.repository.MovieRepository;
import ua.romankh3.movietracking.service.MovieService;
import ua.romankh3.movietracking.service.UserService;
import ua.romankh3.movietracking.tmdb.service.ActorTmdbService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private UserService userService;
    private ActorTmdbService actorTmdbService;

    @Autowired
    public MovieServiceImpl(final MovieRepository movieRepository,
                            final UserService userService,
                            final ActorTmdbService actorTmdbService) {
        this.actorTmdbService = actorTmdbService;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    @Override
    public Movie createMovie(Integer thmbMovieId) {
        Optional<Movie> movieOptional = movieRepository.findByTmdbId(thmbMovieId);
        if(movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            return movieRepository.save(fillNewMovie(thmbMovieId));
        }
    }

    private Movie fillNewMovie(Integer thmbMovieId) {
        Movie movie = new Movie();
        movie.setTmdbId(thmbMovieId);
        List<Actor> actors = actorTmdbService.findActorsByMovie(thmbMovieId);
        movie.getActors().addAll(actors);
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
