package ua.romankh3.movie.tracking.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.mapper.MovieTMDB;
import ua.romankh3.movie.tracking.service.TmdbAPIService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.StringJoiner;


@Service
public class TmdbAPIServiceImpl implements TmdbAPIService {

    @Value("${tmdb.v3.apikey}")
    private String tmdbApiKey;

    @Value("${tmdb.language}")
    private String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    private static final String API_KEY = "api_key";
    private static final String LANGUAGE = "language";
    private static final String PRIMARY_RELEASE_YEAR = "primary_release_year";
    private static final String FAVORITE_ACTORS = "with_people";
    private static final String DISCOVER_MOVIE = "/discover/movie";
    private static final String DISCOVER_ACTOR = "/discover/actor";

    @Override
    public List<MovieTMDB> retrieveMovies() throws IOException {
        return callToTMDB(DISCOVER_MOVIE, null, null);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(String primaryReleaseYear) {
        return callToTMDB(DISCOVER_MOVIE, primaryReleaseYear, null);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(List<Integer> favoriteActorIds) {
        return callToTMDB(DISCOVER_MOVIE, null, favoriteActorIds);
    }

    @Override
    public List<MovieTMDB> retrieveMovies(String primaryReleaseYear, List<Integer> favoriteActorIds) {
        return callToTMDB(DISCOVER_MOVIE, primaryReleaseYear, favoriteActorIds);
    }

    private List<MovieTMDB> callToTMDB(String path,
                              String primaryReleaseYear,
                              List<Integer> favoriteActorIds) {
        try {
            String url = createTmdbUrl(path, primaryReleaseYear, favoriteActorIds);
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
                                 String primaryReleaseYear,
                                 List<Integer> favoriteActors) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + path);
        uriBuilder.addParameter(LANGUAGE, tmdbLanguage);
        uriBuilder.addParameter(API_KEY, tmdbApiKey);

        if(favoriteActors != null && !favoriteActors.isEmpty()) {
            uriBuilder.addParameter(FAVORITE_ACTORS, joinIdsToString(favoriteActors));
        }

        if(primaryReleaseYear != null) {
            uriBuilder.addParameter(PRIMARY_RELEASE_YEAR, primaryReleaseYear);
        }
        return uriBuilder.build().toString();
    }

    private String joinIdsToString(List<Integer> ids) {
        StringJoiner stringJoiner = new StringJoiner(",");
        ids.forEach(it -> stringJoiner.add(String.valueOf(it)));
        return stringJoiner.toString();
    }


}
