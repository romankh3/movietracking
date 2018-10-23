package ua.romankh3.movietracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movietracking.model.Actor;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    Optional<Actor> findById(final Integer id);
    Optional<Actor> findByThdbId(final Integer tmdbId);
}
