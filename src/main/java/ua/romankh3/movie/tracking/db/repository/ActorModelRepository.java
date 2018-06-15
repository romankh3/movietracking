package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.repository.Repository;
import ua.romankh3.movie.tracking.db.model.ActorModel;

import java.util.List;
import java.util.Optional;

public interface ActorModelRepository extends Repository<ActorModel, Integer> {

    List<ActorModel> findAll();

    Optional<ActorModel> findById(final Integer id);

    Optional<ActorModel> findByTmdbId(final Integer tmdbId);

    ActorModel save(final ActorModel actorModel);
}
