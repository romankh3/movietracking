package ua.romankh3.movietracking.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.romankh3.movietracking.model.Movie;
import ua.romankh3.movietracking.service.MovieService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceIntegrationTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void testCreateMovie() {
        Integer tmdbId = 550;
        Movie movie = movieService.createMovie(tmdbId);
        Assert.assertNotNull(movie);
        Assert.assertEquals(tmdbId, movie.getTmdbId());
    }
}