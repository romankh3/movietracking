package ua.romankh3.movietracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.model.User;
import ua.romankh3.movietracking.service.AuthenticationService;
import ua.romankh3.movietracking.tmdb.model.MovieTMDB;
import ua.romankh3.movietracking.tmdb.service.ActorTmdbService;
import ua.romankh3.movietracking.tmdb.service.MovieTmbdService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MovieTmbdService movieTmbdService;

    @Autowired
    private ActorTmdbService actorTmdbService;

    private final String pathToImage = "https://image.tmdb.org/t/p/original";

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home() {

        User user = authenticationService.getAuthenticationUser();
        List<MovieTMDB> newestMovies = movieTmbdService.findNowPlaying();


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/home");
        modelAndView.addObject("userMessage", "The newest movies");
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("movies", newestMovies);
        modelAndView.addObject("pathToImage", pathToImage);
        return modelAndView;
    }


    @RequestMapping(value = "/user/movie/{movie_id}", method = RequestMethod.GET)
    public ModelAndView movies(@PathVariable("movie_id") Integer movieId) {
        MovieTMDB movieTMDB = movieTmbdService.findById(movieId);
        List<Actor> actors = actorTmdbService.findActorsByMovie(movieId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("movie", movieTMDB);
        modelAndView.addObject("pathToImage", pathToImage);
        modelAndView.addObject("actors", actors);
        modelAndView.setViewName("/user/movie");

        return modelAndView;
    }
}
