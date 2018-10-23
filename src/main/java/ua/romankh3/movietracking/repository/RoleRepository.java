package ua.romankh3.movietracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.romankh3.movietracking.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    // todo rename it to Name instead of role.
    Role findByRole(String role);
}
