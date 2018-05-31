package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.ActorEntity;
import ua.romankh3.movie.tracking.service.ActorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @RequestMapping(name = "/{user_id}", method = RequestMethod.POST)
    public Integer addFavoriteActor(@PathVariable("user_id") Integer userId,
                                    @RequestBody @Valid ActorEntity actorEntity) throws NotFoundException {
        return actorService.addFavoriteActor(userId, actorEntity);
    }
}
