package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.AlreadyExistException;
import ua.romankh3.movie.tracking.rest.entity.UserEntity;
import ua.romankh3.movie.tracking.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public Integer createUser(@RequestBody @Valid UserEntity userEntity) throws AlreadyExistException {
        return userService.createUser(userEntity).getId();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public UserEntity delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }
}
