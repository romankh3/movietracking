package ua.romankh3.movie.tracking.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movie.tracking.db.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

    List<UserModel> findAll();

    UserModel save(final UserModel userModel);

    Optional<UserModel> findById(final Integer id);

    Optional<UserModel> findByEmail(final String email);

    void deleteById(final Integer id);
}
