package ua.romankh3.movietracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.service.AuthenticationService;
import ua.romankh3.movietracking.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        User user = authenticationService.getAuthenticationUser();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }
}
