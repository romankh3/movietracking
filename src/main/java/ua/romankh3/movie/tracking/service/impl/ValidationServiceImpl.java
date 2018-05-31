package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.repository.UserRepository;
import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;
import ua.romankh3.movie.tracking.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateUser(UserEntity userEntity) throws AlreadyExistException {
        boolean present = userRepository.findByFirstNameAndLastName(userEntity.getFirstName(), userEntity.getLastName()).isPresent();
        if(present) {
            throw new AlreadyExistException("User with this First and Last name already exist!");
        }
    }
}
