package ua.romankh3.movietracking.service;

import ua.romankh3.movietracking.exception.NotFoundException;
import ua.romankh3.movietracking.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);
    User createUser(User user);
    void deleteUser(User user);
    User findUserById(Integer id) throws NotFoundException;
    User findAuthenticatedUser();
    User saveUser(User user);
    List<User> findAll();
}
