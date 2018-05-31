package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;

public interface ValidationService {

    void validateUser(final UserEntity userEntity) throws AlreadyExistException;
}
