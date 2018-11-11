package ua.romankh3.movietracking.tmdb.service;

import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.tmdb.model.ActorCastTMDB;
import ua.romankh3.movietracking.tmdb.model.ActorTMDB;

import java.util.List;

public interface ActorTmdbService {

    List<Actor> findActorsByMovie(Integer movieUd);

    ActorTMDB findByActorId(Integer actor_id);
}
