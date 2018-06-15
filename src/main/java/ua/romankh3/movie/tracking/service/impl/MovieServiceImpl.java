package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.*;
import ua.romankh3.movie.tracking.db.repository.MovieModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_ActorModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_MovieModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.mapper.MovieTMDB;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;
import ua.romankh3.movie.tracking.service.TmdbAPIService;
import ua.romankh3.movie.tracking.service.UserService;

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
    public List<MovieTMDB> retrieveMoviesByFavoriteActors(Integer userId) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(userId);

        List<Integer> watchedMovieIds = retrieveWatchedMovieIds(userModel);
        List<Integer> actorIds = retrieveFavoriteActorIds(userModel);

        List<MovieTMDB> movieTMDBS = retrieveMoviesByActorIdIn(actorIds);
        return movieTMDBS.stream().filter(it -> !watchedMovieIds.contains(it.getId())).collect(Collectors.toList());
    }

    private List<MovieTMDB> retrieveMoviesByActorIdIn(List<Integer> actorIds) {
        return tmdbAPIService.retrieveMovies(actorIds);
    }

    private List<Integer> retrieveFavoriteActorIds(final UserModel userModel) {
        List<User_x_ActorModel> user_x_actorModels = user_x_actorModelRepository.findByUserModel(userModel);
        return user_x_actorModels.stream()
                .map(it -> it.getId().getActor_id())
                .collect(Collectors.toList());
    }

    private List<Integer> retrieveWatchedMovieIds(final UserModel userModel) {
        return user_x_movieModelRepository.findByUserModel(userModel)
                .stream()
                .filter(it -> !it.getWatched())
                .map(it -> it.getMovieModel().getId())
                .collect(Collectors.toList());
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
