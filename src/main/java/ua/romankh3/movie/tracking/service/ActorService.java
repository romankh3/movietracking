package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;

import java.util.List;

public interface ActorService {

    List<ActorEntity> findAll();
}
