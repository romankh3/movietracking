package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.repository.CrudRepository;
import ua.romankh3.movie.tracking.db.model.User_x_MovieModel;
import ua.romankh3.movie.tracking.db.model.User_x_MoviePK;

public interface User_x_MovieModelRepository extends CrudRepository<User_x_MovieModel, User_x_MoviePK> {
}
