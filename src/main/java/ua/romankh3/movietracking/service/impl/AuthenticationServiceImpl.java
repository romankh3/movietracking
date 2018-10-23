package ua.romankh3.movietracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.service.AuthenticationService;
import ua.romankh3.movietracking.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserService userService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getAuthenticationUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}
