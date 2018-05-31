package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.db.repository.ActorModelRepository;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
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
    private UserService userService;


    @Override
    public List<ActorEntity> findAll() {
        return actorModelRepository.findAll()
                .stream()
                .map(this::convertModelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addFavoriteActor(Integer userId, ActorEntity actorEntity) throws NotFoundException {
        UserModel userModel = userService.retrieveUserByIdAndShouldNotBeNull(userId);
        Optional<ActorModel> actorModelOptional = actorModelRepository.findByFirstNameAndLastName(actorEntity.getFirstName(),
                                                                                                  actorEntity.getLastName());
        ActorModel actorModel = actorModelOptional.orElseGet(() -> createActor(actorEntity));
        // todo implement logic for adding favorite actor to User_x_Actor.
        return actorModel.getId();
    }

    @Override
    public ActorModel createActor(ActorEntity actorEntity) {
        return actorModelRepository.save(convertEntityToModel(actorEntity));
    }

    private ActorModel convertEntityToModel(final ActorEntity actorEntity) {
        ActorModel actorModel = new ActorModel();
        actorModel.setFirstName(actorEntity.getFirstName());
        actorModel.setLastName(actorEntity.getLastName());
        return actorModel;
    }

    private ActorEntity convertModelToEntity(ActorModel model) {
        ActorEntity entity = new ActorEntity();
        entity.setActor_id(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        return entity;
    }
}
