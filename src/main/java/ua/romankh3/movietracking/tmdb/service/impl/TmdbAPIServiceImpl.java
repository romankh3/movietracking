package ua.romankh3.movietracking.tmdb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.tmdb.model.MovieTMDB;
import ua.romankh3.movietracking.tmdb.service.TmdbAPIService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

import static ua.romankh3.movietracking.tmdb.service.impl.TmdbAPIServiceImpl.TMDBConstants.*;

@Service
public class TmdbAPIServiceImpl implements TmdbAPIService {

    @Value("${tmdb.v3.apikey}")
    private String tmdbApiKey;

    @Value("${tmdb.language}")
    private String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;



    @Override
    public List<MovieTMDB> retrieveMovies() throws IOException {
        return callToTMDB(DISCOVER_MOVIE, null, null, null);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear) {
        return callToTMDB(DISCOVER_MOVIE, primaryReleaseYear, null, null);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(List<Integer> favoriteActorIds) {
        return callToTMDB(DISCOVER_MOVIE, null, null, favoriteActorIds);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear, List<Integer> favoriteActorIds) {
        return callToTMDB(DISCOVER_MOVIE, primaryReleaseYear, null, favoriteActorIds);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(Integer primaryReleaseYear, Integer month, List<Integer> favoriteActorIds) {
        return callToTMDB(DISCOVER_MOVIE, primaryReleaseYear, month, favoriteActorIds);
    }

    private List<MovieTMDB> callToTMDB(String path,
                                       Integer primaryReleaseYear,
                                       Integer month,
                                       List<Integer> favoriteActorIds) {
        try {
            String url = createTmdbUrl(path, primaryReleaseYear, month, favoriteActorIds);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

            if(jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }
            String jsonList = jsonResponse.getBody().getObject().get("results").toString();
            ObjectMapper objectMapper = new ObjectMapper();
            List<MovieTMDB> movieTMDBList = objectMapper.readValue(jsonList, new TypeReference<List<MovieTMDB>>(){} );

            return movieTMDBList;
        } catch (URISyntaxException | UnirestException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String createTmdbUrl(String path,
                                 Integer primaryReleaseYear,
                                 Integer month,
                                 List<Integer> favoriteActors) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + path);
        uriBuilder.addParameter(LANGUAGE, tmdbLanguage);
        uriBuilder.addParameter(API_KEY, tmdbApiKey);

        if(favoriteActors != null && !favoriteActors.isEmpty()) {
            uriBuilder.addParameter(FAVORITE_ACTORS, joinIdsToString(favoriteActors));
        }

        if(primaryReleaseYear != null && month == null) {
            uriBuilder.addParameter(PRIMARY_RELEASE_YEAR, String.valueOf(primaryReleaseYear));
        } else if(primaryReleaseYear != null) {
            LocalDate start = LocalDate.of(primaryReleaseYear, month, 1);
            LocalDate end = month == 12?
                    LocalDate.of(primaryReleaseYear + 1, 1, 1).minusDays(1):
                    LocalDate.of(primaryReleaseYear, month, 1).minusDays(1);
            uriBuilder.addParameter(PRIMARY_RELEASE_DATE_GTE, start.format(DATE_TIME_FORMATTER));
            uriBuilder.addParameter(PRIMARY_RELEASE_DATE_LTE, end.format(DATE_TIME_FORMATTER));
        }
        return uriBuilder.build().toString();
    }

    private String joinIdsToString(List<Integer> ids) {
        StringJoiner stringJoiner = new StringJoiner(",");
        ids.forEach(it -> stringJoiner.add(String.valueOf(it)));
        return stringJoiner.toString();
    }

    static class TMDBConstants {
        public static final String API_KEY = "api_key";
        public static final String LANGUAGE = "language";
        public static final String NOW_PLAYING_MOVIE = "/movie/now_playing";
        public static final String PRIMARY_RELEASE_YEAR = "primary_release_year";
        public static final String PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
        public static final String PRIMARY_RELEASE_DATE_LTE = "primary_release_date.lte";
        public static final String FAVORITE_ACTORS = "with_people";
        public static final String DISCOVER_MOVIE = "/discover/movie";
        public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d");
    }

}
