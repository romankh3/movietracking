package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.ActorModel;
import ua.romankh3.movie.tracking.db.repository.ActorModelRepository;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
import ua.romankh3.movie.tracking.service.ActorService;

import java.nio.file.AccessMode;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorModelRepository actorModelRepository;


    @Override
    public List<ActorEntity> findAll() {
        return actorModelRepository.findAll()
                .stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    private ActorEntity modelToEntity(ActorModel model) {
        ActorEntity entity = new ActorEntity();
        entity.setActor_id(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        return entity;
    }
}
