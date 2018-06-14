package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getPopularMovies() throws IOException {
        return movieService.retrievePopularMovies("/movie/popular");
    }

    @PostMapping("/watched")
    public void markMovieWatched(@RequestBody @Valid WatchedMovieEntity watchedMovieEntity) throws NotFoundException {
        movieService.markMovieAsWatched(watchedMovieEntity);
    }

    @PostMapping("/unwatched")
    public void markMovieUnwatched(@RequestBody @Valid WatchedMovieEntity watchedMovieEntity) throws NotFoundException {
        movieService.markMovieAsUnWatched(watchedMovieEntity);
    }

    @GetMapping("/{user_id}/")
    public String getMoviesWithFavoriteActors(@PathVariable("user_id") Integer userId) {
        return movieService.retrieveMoviesByFavoriteActors(userId);
    }
}
