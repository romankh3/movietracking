package ua.romankh3.movietracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.service.AuthenticationService;
import ua.romankh3.movietracking.tmdb.model.MovieTMDB;
import ua.romankh3.movietracking.tmdb.service.MovieTmbdService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MovieTmbdService movieTmbdService;

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home() {

        User user = authenticationService.getAuthenticationUser();
        List<MovieTMDB> newestMovies = movieTmbdService.findNowPlaying();


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/home");
        modelAndView.addObject("userMessage", "This is your place, where you can explore Movies!");
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("movies", newestMovies);
        return modelAndView;
    }


    @RequestMapping(value = "/user/movies", method = RequestMethod.GET)
    public ModelAndView movies() {
        User user = authenticationService.getAuthenticationUser();
        List<MovieTMDB> newestMovies = movieTmbdService.findNowPlaying();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newestMovies", "Explore Movies");
        modelAndView.addObject("movies", newestMovies);

        return modelAndView;
    }
}
