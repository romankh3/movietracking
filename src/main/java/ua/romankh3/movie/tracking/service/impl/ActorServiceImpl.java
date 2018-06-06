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

    @Autowired
    private UserService userService;

    @Override
    public List<ActorEntity> findAll() {
        return actorModelRepository.findAll()
                .stream()
                .map(this::convertModelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveUserByIdAndShouldNotBeNull(favoriteActorEntity.getUser_id());
        Optional<ActorModel> actorModelOptional = actorModelRepository.findById(favoriteActorEntity.getActor_id());
        ActorModel actorModel = actorModelOptional.orElseGet(() -> createActor(favoriteActorEntity));

        user_x_actorModelRepository.save(fillUser_x_Actor(userModel.getId(), actorModel.getId(), true));
        return actorModel.getId();
    }

    @Override
    public Integer removeFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveUserByIdAndShouldNotBeNull(favoriteActorEntity.getUser_id());
        Optional<ActorModel> actorModelOptional = actorModelRepository.findById(favoriteActorEntity.getActor_id());
        if(!actorModelOptional.isPresent()) {
            return 0;
        }
        user_x_actorModelRepository.save(fillUser_x_Actor(userModel.getId(), actorModelOptional.get().getId(), false));
        return actorModelOptional.get().getId();
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
        return actorModelRepository.save(convertEntityToModel(actorEntity));
    }

    private ActorModel convertEntityToModel(final ActorEntity actorEntity) {
        ActorModel actorModel = new ActorModel();
        actorModel.setId(actorEntity.getActor_id());
        return actorModel;
    }

    private ActorEntity convertModelToEntity(ActorModel model) {
        ActorEntity entity = new ActorEntity();
        entity.setActor_id(model.getId());
        return entity;
    }
}
