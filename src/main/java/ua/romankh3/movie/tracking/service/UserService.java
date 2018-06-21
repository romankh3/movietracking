package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity createUser(final UserEntity userEntity) throws Exception;

    void delete(final Integer id);

    UserModel retrieveExistingEntity(final Integer id) throws NotFoundException;
}
