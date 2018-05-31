package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;
import ua.romankh3.movie.tracking.db.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserModel createUser(final UserEntity userEntity) throws AlreadyExistException;

    UserEntity delete(final Integer id);

    UserModel retrieveUserByIdAndShouldNotBeNull(final Integer id) throws NotFoundException;
}
