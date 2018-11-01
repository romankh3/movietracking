package ua.romankh3.movietracking.tmdb.service;

import ua.romankh3.movietracking.model.Actor;

import java.util.List;

public interface ActorTmdbService {

    List<Actor> findActorsByMovie(Integer movieUd);
}
