package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.db.model.User_x_ActorModel;
import ua.romankh3.movie.tracking.db.model.User_x_ActorPK;
import ua.romankh3.movie.tracking.db.repository.ActorModelRepository;
import ua.romankh3.movie.tracking.db.repository.User_x_ActorModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
import ua.romankh3.movie.tracking.rest.entity.FavoriteActorEntity;
import ua.romankh3.movie.tracking.service.ActorService;
import ua.romankh3.movie.tracking.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorModelRepository actorModelRepository;

    @Autowired
    private User_x_ActorModelRepository user_x_actorModelRepository;

    private static final boolean FAVORITE = true;
    private static final boolean UN_FAVORITE = false;

    @Autowired
    private UserService userService;

    @Override
    public ActorEntity addFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(favoriteActorEntity.getUser_id());
        Optional<ActorModel> actorModelOptional = actorModelRepository.findByTmdbId(favoriteActorEntity.getActor_id());
        ActorModel actorModel = actorModelOptional.orElseGet(() -> createActor(favoriteActorEntity));

        return toEntity(user_x_actorModelRepository.save(fillUser_x_Actor(userModel.getId(),
                                                         actorModel.getId(),
                                                         FAVORITE)).getActorModel());
    }

    @Override
    public ActorEntity removeFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(favoriteActorEntity.getUser_id());
        Optional<ActorModel> actorModelOptional = actorModelRepository.findById(favoriteActorEntity.getActor_id());
        return toEntity(user_x_actorModelRepository.save(fillUser_x_Actor(userModel.getId(),
                actorModelOptional.get().getId(),
                UN_FAVORITE)).getActorModel());
    }

    private User_x_ActorModel fillUser_x_Actor(Integer userId, Integer actorId, boolean favorite) {
        User_x_ActorPK pk = new User_x_ActorPK();
        pk.setActor_id(actorId);
        pk.setUser_id(userId);
        User_x_ActorModel user_x_actorModel = new User_x_ActorModel();
        user_x_actorModel.setId(pk);
        user_x_actorModel.setFavorite(favorite);
        return user_x_actorModel;
    }

    @Override
    public ActorModel createActor(ActorEntity actorEntity) {
        return actorModelRepository.save(toModel(actorEntity));
    }

    @Override
    public List<ActorEntity> findByUserId(Integer userId) throws NotFoundException {
        UserModel userModel = userService.retrieveExistingEntity(userId);

        return user_x_actorModelRepository.findByUserModel(userModel).stream()
                .map(User_x_ActorModel::getActorModel)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    private ActorModel toModel(final ActorEntity actorEntity) {
        ActorModel actorModel = new ActorModel();
        actorModel.setTmdbId(actorEntity.getActor_id());
        return actorModel;
    }

    private ActorEntity toEntity(ActorModel model) {
        ActorEntity entity = new ActorEntity();
        entity.setActor_id(model.getTmdbId());
        return entity;
    }
}
