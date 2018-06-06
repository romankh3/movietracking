package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.FavoriteActorEntity;
import ua.romankh3.movie.tracking.service.ActorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/favoriteactor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping
    public Integer addFavoriteActor(@RequestBody @Valid FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        return actorService.addFavoriteActor(favoriteActorEntity);
    }

    @PutMapping
    public Integer removeFavoriteActor(@RequestBody @Valid FavoriteActorEntity favoriteActorEntity) throws NotFoundException {
        return actorService.removeFavoriteActor(favoriteActorEntity);
    }
}
