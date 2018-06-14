package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.MovieModel;
import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.db.model.User_x_MovieModel;
import ua.romankh3.movie.tracking.db.model.User_x_MoviePK;
import ua.romankh3.movie.tracking.db.repository.MovieModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_MovieModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;
import ua.romankh3.movie.tracking.service.TmdbAPIService;
import ua.romankh3.movie.tracking.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Boolean WATCHED = true;
    private static final Boolean UNWATCHED = false;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieModelRepository movieModelRepository;

    @Autowired
    private User_x_MovieModelRepository user_x_movieModelRepository;

    @Autowired
    private TmdbAPIService tmdbAPIService;

    @Override
    public String retrievePopularMovies(String path) throws IOException {
        return tmdbAPIService.retrieveMovies(path);
    }

    @Override
    public MovieModel createMovieModel(final MovieEntity movieEntity) {
        return movieModelRepository.save(toModel(movieEntity));
    }

    @Override
    public void markMovieAsWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException {
        markMovie(watchedMovieEntity, WATCHED);
    }

    @Override
    public void markMovieAsUnWatched(final WatchedMovieEntity watchedMovieEntity) throws NotFoundException {
        markMovie(watchedMovieEntity, UNWATCHED);
    }

    @Override
    public String retrieveMoviesByFavoriteActors(Integer userId) {
        return null;
    }

    private void markMovie(final WatchedMovieEntity watchedMovieEntity, boolean isWatched) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(watchedMovieEntity.getUser_id());
        Optional<MovieModel> movieModelOptional = movieModelRepository.findById(watchedMovieEntity.getMovie_id());
        MovieModel movieModel = movieModelOptional.orElseGet(() -> createMovieModel(watchedMovieEntity));

        user_x_movieModelRepository.save(createUser_x_MovieModel(userModel, movieModel, isWatched));
    }

    private User_x_MovieModel createUser_x_MovieModel(UserModel userModel,
                                                      MovieModel movieModel,
                                                      Boolean isWatched) {
        User_x_MovieModel user_x_movieModel = new User_x_MovieModel();
        User_x_MoviePK user_x_moviePK = new User_x_MoviePK();
        user_x_moviePK.setUser_id(userModel.getId());
        user_x_moviePK.setMovie_id(movieModel.getId());

        user_x_movieModel.setId(user_x_moviePK);
        user_x_movieModel.setWatched(isWatched);
        return user_x_movieModel;
    }

    private MovieModel toModel(final MovieEntity movieEntity) {
        MovieModel mm = new MovieModel();
        mm.setName(movieEntity.getName());
        mm.setYear(movieEntity.getYear());
        mm.setId(movieEntity.getMovie_id());
        return mm;
    }
}
