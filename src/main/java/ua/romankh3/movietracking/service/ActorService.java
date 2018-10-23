package ua.romankh3.movietracking.service;

import ua.romankh3.movietracking.model.Actor;

public interface ActorService {

    void addFavoriteActor(final Actor actor);
    void removeFavoriteActor(final Actor actor);
}
