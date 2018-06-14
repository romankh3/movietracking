package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.MovieModel;
import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.db.repository.MovieModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;
import ua.romankh3.movie.tracking.service.UserService;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieModelRepository movieModelRepository;

    @Override
    public MovieModel createMovieModel(final MovieEntity movieEntity) {
        return null;
    }

    @Override
    public void markMovieAsWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(watchedMovieEntity.getUser_id());
        Optional<MovieModel> movieModelOptional = movieModelRepository.findById(watchedMovieEntity.getMovie_id());
        MovieModel movieModel = movieModelOptional.orElseGet(() -> createMovieModel(watchedMovieEntity));
    }

    @Override
    public void markMovieAsUnWatched(final WatchedMovieEntity watchedMovieEntity) {

    }

    @Override
    public String retrieveMoviesByFavoriteActors(Integer userId) {

        return null;
    }
}
