package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.*;
import ua.romankh3.movie.tracking.db.repository.MovieModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_ActorModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_MovieModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.exception.ValidationException;
import ua.romankh3.movie.tracking.mapper.MovieTMDB;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;
import ua.romankh3.movie.tracking.service.TmdbAPIService;
import ua.romankh3.movie.tracking.service.UserService;
import ua.romankh3.movie.tracking.service.ValidationService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private User_x_ActorModelRepository user_x_actorModelRepository;

    @Autowired
    private TmdbAPIService tmdbAPIService;

    @Autowired
    private ValidationService validationService;

    @Override
    public MovieModel createMovieModel(final MovieEntity movieEntity) {
        MovieModel movieModel = toModel(movieEntity);
        return movieModelRepository.save(movieModel);
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
    public List<MovieTMDB> retrieveMoviesByFavoriteActors(Integer userId) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(userId);

        List<Integer> watchedMovieIds = retrieveWatchedMovieIds(userModel);
        List<Integer> actorIds = retrieveFavoriteActorIds(userModel);

        return tmdbAPIService.retrieveMovies(actorIds).stream()
                                                      .filter(it -> !watchedMovieIds.contains(it.getId()))
                                                      .collect(Collectors.toList());
    }

    @Override
    public List<MovieTMDB> retrieveMoviesByActorsAndReleaseYear(Integer userId, Integer year) throws NotFoundException, ValidationException {
        validationService.validateYear(year);

        UserModel userModel = userService.retrieveExistingEntity(userId);
        List<Integer> actorIds = retrieveFavoriteActorIds(userModel);

        return filterOnlyUnwatchedMovies(tmdbAPIService.retrieveMovies(year, actorIds), userModel);
    }

    @Override
    public List<MovieTMDB> retrieveMoviesByActorsAndReleaseMonth(Integer userId, Integer year, Integer month) throws NotFoundException, ValidationException, IOException {
        validationService.validateYear(year);
        validationService.validateMonth(month);

        UserModel userModel = userService.retrieveExistingEntity(userId);
        List<Integer> actorIds = retrieveFavoriteActorIds(userModel);

        return filterOnlyUnwatchedMovies(tmdbAPIService.retrieveMovies(year, month, actorIds), userModel);
    }

    private List<MovieTMDB> filterOnlyUnwatchedMovies(List<MovieTMDB> movieTMDBS, UserModel userModel) {
        List<Integer> watchedMovieIds = retrieveWatchedMovieIds(userModel);
        return movieTMDBS.stream()
                .filter(it -> !watchedMovieIds.contains(it.getId()))
                .collect(Collectors.toList());
    }

    private List<Integer> retrieveWatchedMovieIds(final UserModel userModel) {
        return user_x_movieModelRepository.findByUserModel(userModel)
                .stream()
                .filter(User_x_MovieModel::getWatched)
                .map(it -> it.getMovieModel().getTmdbId())
                .collect(Collectors.toList());
    }

    private List<Integer> retrieveFavoriteActorIds(final UserModel userModel) {
        List<User_x_ActorModel> user_x_actorModels = user_x_actorModelRepository.findByUserModel(userModel);
        return user_x_actorModels.stream()
                .map(it -> it.getActorModel().getTmdbId())
                .collect(Collectors.toList());
    }

    private void markMovie(final WatchedMovieEntity watchedMovieEntity, boolean isWatched) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(watchedMovieEntity.getUser_id());
        Optional<MovieModel> movieModelOptional = movieModelRepository.findByTmdbId(watchedMovieEntity.getMovie_id());
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
        mm.setTmdbId(movieEntity.getMovie_id());
        return mm;
    }
}
