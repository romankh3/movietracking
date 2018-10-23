package ua.romankh3.movietracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movietracking.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByName(String name);
}
