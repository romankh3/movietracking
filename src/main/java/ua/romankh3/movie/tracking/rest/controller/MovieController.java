package ua.romankh3.movie.tracking.rest.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.romankh3.movie.tracking.rest.entity.MovieEntity;
import ua.romankh3.movie.tracking.service.TmdbAPIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TmdbAPIService tmdbAPIService;

    @GetMapping
    public List<MovieEntity> getAllMovies() throws IOException {
        String result = tmdbAPIService.retrieveMovies("/movie/popular");
        return new ArrayList<>();
    }
}
