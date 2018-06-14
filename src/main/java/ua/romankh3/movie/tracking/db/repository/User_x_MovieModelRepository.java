package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movie.tracking.db.model.User_x_MovieModel;
import ua.romankh3.movie.tracking.db.model.User_x_MoviePK;

import java.util.List;

public interface User_x_MovieModelRepository extends JpaRepository<User_x_MovieModel, User_x_MoviePK> {

    User_x_MovieModel save(final User_x_MovieModel user_x_actorModel);

    List<User_x_MovieModel> findAll();
}
