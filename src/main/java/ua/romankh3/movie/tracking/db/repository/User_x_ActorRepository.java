package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movie.tracking.db.model.User_x_ActorModel;

import java.util.List;

public interface User_x_ActorRepository extends JpaRepository<User_x_ActorModel, Integer> {

    User_x_ActorModel save(final User_x_ActorModel user_x_actorModel);

    List<User_x_ActorModel> findAll();
}
