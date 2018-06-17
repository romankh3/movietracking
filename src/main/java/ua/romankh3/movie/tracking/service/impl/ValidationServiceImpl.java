package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.repository.UserModelRepository;
import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.exception.ValidationException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;
import ua.romankh3.movie.tracking.service.ValidationService;

import java.time.LocalDate;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public void validateUser(UserEntity userEntity) throws AlreadyExistException {
        if(userModelRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new AlreadyExistException("User with this email(" + userEntity.getEmail() + ") already exist!");
        }
    }

    @Override
    public void validateYear(Integer year) throws ValidationException {
        Integer nowYear = LocalDate.now().getYear();
        if(1900 > year || year > nowYear + 50) {
            throw new ValidationException("The year of the movie should be more than 1900 " +
                    "and less than "+ (nowYear + 50));
        }
    }

    @Override
    public void validateMonth(Integer month) throws ValidationException {
        if(month < 1 || month > 12) {
            throw new ValidationException("The month should be more than 0 and less than 13");
        }
    }
}
