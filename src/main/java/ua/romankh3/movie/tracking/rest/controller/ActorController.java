package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
import ua.romankh3.movie.tracking.rest.entity.FavoriteActorEntity;
import ua.romankh3.movie.tracking.service.ActorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping(name = "/favorite")
    public ActorEntity addFavoriteActor(@RequestBody @Valid FavoriteActorEntity favoriteActorEntity)
            throws NotFoundException {

        return actorService.addFavoriteActor(favoriteActorEntity);
    }

    @PutMapping(name = "favorite")
    public ActorEntity removeFavoriteActor(@RequestBody @Valid FavoriteActorEntity favoriteActorEntity)
            throws NotFoundException {

        return actorService.removeFavoriteActor(favoriteActorEntity);
    }

    @RequestMapping(value = "/favorite/{user_id}", method = RequestMethod.GET)
    public List<ActorEntity> getFavoriteActorsByUserId(@PathVariable("user_id") Integer userId)
            throws NotFoundException {

        return actorService.findByUserId(userId);
    }
}