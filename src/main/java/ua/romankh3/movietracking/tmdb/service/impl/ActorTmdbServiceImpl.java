package ua.romankh3.movietracking.tmdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.tmdb.model.ActorCastTMDB;
import ua.romankh3.movietracking.tmdb.service.ActorTmdbService;
import ua.romankh3.movietracking.tmdb.service.TmdbAPIService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorTmdbServiceImpl implements ActorTmdbService {

    private TmdbAPIService tmdbAPIService;

    private final String pathToImage = "https://image.tmdb.org/t/p/original";

    @Autowired
    public ActorTmdbServiceImpl(TmdbAPIService tmdbAPIService) {
        this.tmdbAPIService = tmdbAPIService;
    }

    @Override
    public List<Actor> findActorsByMovie(Integer movieUd) {
        String path = "/movie/" + movieUd + "/credits";
        List<ActorCastTMDB> castByMovie = tmdbAPIService.findCastByMovie(path);
        return castByMovie.stream().map(this::toActor).collect(Collectors.toList());
    }

    private Actor toActor(ActorCastTMDB castTMDB) {
        Actor actor = new Actor();
        actor.setActive(1);
        actor.setFullName(castTMDB.getFullActorName());
        actor.setThdbId(castTMDB.getActorId());
        if(castTMDB.getProfilePath() != null) {
            actor.setPicturePath(castTMDB.getProfilePath());
        } else {
            actor.setPicturePath("/images/no-image-avaliable.jpg");
        }

        actor.setPicturePath(castTMDB.getProfilePath());
        return actor;
    }
}
