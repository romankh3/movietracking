package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
import ua.romankh3.movie.tracking.rest.entity.FavoriteActorEntity;

import java.util.List;

public interface ActorService {

    List<ActorEntity> findAll();

    Integer addFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException;

    Integer removeFavoriteActor(final FavoriteActorEntity favoriteActorEntity) throws NotFoundException;

    ActorModel createActor(final ActorEntity actorEntity);
}
