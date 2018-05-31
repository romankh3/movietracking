package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;

import java.util.List;

public interface ActorService {

    List<ActorEntity> findAll();

    Integer addFavoriteActor(final Integer userId, final ActorEntity actorEntity) throws NotFoundException;

    ActorModel createActor(final ActorEntity actorEntity);
}
