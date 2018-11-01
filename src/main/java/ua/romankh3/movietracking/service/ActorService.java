package ua.romankh3.movietracking.service;

import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.tmdb.model.ActorTMDB;

import java.util.List;

public interface ActorService {

    void addFavoriteActor(final Actor actor);
    void removeFavoriteActor(final Actor actor);
}
