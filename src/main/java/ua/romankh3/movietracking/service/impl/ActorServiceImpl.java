package ua.romankh3.movietracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.repository.UserRepository;
import ua.romankh3.movietracking.service.ActorService;
import ua.romankh3.movietracking.service.UserService;

@Service
public class ActorServiceImpl implements ActorService {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public ActorServiceImpl(final UserRepository userRepository,
                            final UserService  userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void addFavoriteActor(Actor actor) {
        User user = userService.findAuthenticatedUser();
        user.getActors().add(actor);
        userRepository.save(user);
    }

    @Override
    public void removeFavoriteActor(Actor actor) {
        User user = userService.findAuthenticatedUser();
        user.getActors().remove(actor);
        userRepository.save(user);
    }
}
