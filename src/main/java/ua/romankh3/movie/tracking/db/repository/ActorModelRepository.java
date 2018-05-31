package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movie.tracking.db.model.ActorModel;

import java.util.List;
import java.util.Optional;

public interface ActorModelRepository extends JpaRepository<ActorModel, Integer> {

    List<ActorModel> findAll();

    Optional<ActorModel> findById(final Integer id);

    ActorModel save(final ActorModel actorModel);

    Optional<ActorModel> findByFirstNameAndLastName(String firstName, String lastName);
}
