package ua.romankh3.movie.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.db.model.UserModel;
import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;
import ua.romankh3.movie.tracking.db.repository.UserModelRepository;
import ua.romankh3.movie.tracking.service.UserService;
import ua.romankh3.movie.tracking.service.ValidationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public UserModel createUser(UserEntity userEntity) throws AlreadyExistException {
        validationService.validateUser(userEntity);
        return userModelRepository.save(toUserModel(userEntity));
    }

    @Override
    public List<UserEntity> findAll() {
        return userModelRepository.findAll().stream()
                .map(this::toUserEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity delete(Integer id) {
        return toUserEntity(userModelRepository.deleteById(id));
    }

    @Override
    public UserModel retrieveUserByIdAndShouldNotBeNull(Integer id) throws NotFoundException {
        Optional<UserModel> userOptional = userModelRepository.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException("Not found user with id = " + id);
        }
    }

    private UserEntity toUserEntity(final UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setUser_id(userModel.getId());
        return userEntity;
    }

    private UserModel toUserModel(final UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        return userModel;
    }
}
