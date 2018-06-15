package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.romankh3.movie.tracking.exception.NotFoundException;
import ua.romankh3.movie.tracking.mapper.MovieTMDB;
import ua.romankh3.movie.tracking.rest.entity.WatchedMovieEntity;
import ua.romankh3.movie.tracking.service.MovieService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/watched")
    public void markMovieWatched(@RequestBody @Valid WatchedMovieEntity watchedMovieEntity) throws NotFoundException {

        movieService.markMovieAsWatched(watchedMovieEntity);
    }

    @PostMapping("/unwatched")
    public void markMovieUnwatched(@RequestBody @Valid WatchedMovieEntity watchedMovieEntity) throws NotFoundException {

        movieService.markMovieAsUnWatched(watchedMovieEntity);
    }

    @GetMapping("/{user_id}/")
    public List<MovieTMDB> getUnwatchedMoviesWithFavoriteActors(@PathVariable("user_id") Integer userId)
            throws NotFoundException {

        return movieService.retrieveMoviesByFavoriteActors(userId);
    }
}
