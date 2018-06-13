package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.romankh3.movie.tracking.service.TmdbAPIService;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TmdbAPIService tmdbAPIService;

    @GetMapping
    public String getAllMovies() throws IOException {
        return tmdbAPIService.retrieveMovies("/movie/popular");
    }
}
