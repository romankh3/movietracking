package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.repository.Repository;
import ua.romankh3.movie.tracking.db.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserModelRepository extends Repository<UserModel, Integer> {

    List<UserModel> findAll();

    UserModel save(final UserModel userModel);

    Optional<UserModel> findById(final Integer id);

    Optional<UserModel> findByEmail(final String email);

    UserModel deleteById(final Integer id);
}
