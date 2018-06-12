package ua.romankh3.movie.tracking.service.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.service.TmdbAPIService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@Service
public class TmdbAPIServiceImpl implements TmdbAPIService {

    @Value("${tmdb.v3.apikey}")
    private String tmdbApiKey;

    @Value("${tmdb.language}")
    private String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    public static final String API_KEY = "api_key";
    public static final String LANGUAGE = "language";
    public static final String PRIMARY_RELEASE_YEAR = "primary_release_year";
    public static final String PRIMARY_RELEASE_MONTH = "???";
    public static final String FAVORITE_ACTORS = "people";

    @Override
    public String retrieveMovies(String path) throws IOException {
        return callToTMDB(path, null, null, null);
    }

    @Override
    public String retrieveMovies(String path, String primaryReleaseYear) {
        return callToTMDB(path, primaryReleaseYear, null, null);
    }

    @Override
    public String retrieveMovies(String path, String primaryReleaseYear, String primaryReleaseMonth) {
        return callToTMDB(path, primaryReleaseYear, primaryReleaseMonth, null);
    }

    @Override
    public String retrieveMovies(String path, List<Integer> favoriteActorIds) {
        return callToTMDB(path, null, null, favoriteActorIds);
    }

    @Override
    public String retrieveMovies(String path, String primaryReleaseYear, List<Integer> favoriteActorIds) {
        return callToTMDB(path, primaryReleaseYear, null, favoriteActorIds);
    }

    @Override
    public String retrieveMovies(String path,
                                 String primaryReleaseYear,
                                 String primaryReleaseMonth,
                                 List<Integer> favoriteActorIds) {
        return callToTMDB(path, primaryReleaseYear, primaryReleaseMonth, favoriteActorIds);
    }

    private String callToTMDB(String path,
                              String primaryReleaseYear,
                              String primaryReleaseMonth,
                              List<Integer> favoriteActorIds) {
        try {
            String url = createTmdbUrl(path, primaryReleaseYear, primaryReleaseMonth, favoriteActorIds);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

            if(jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }

            return jsonResponse.getBody().toString();
        } catch (URISyntaxException | UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createTmdbUrl(String path,
                                 String primaryReleaseYear,
                                 String primaryReleaseMonth,
                                 List<Integer> favoriteActors) throws URISyntaxException {
        // todo implement the logic favorite actors and primary release date.
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + path);
        uriBuilder.addParameter(LANGUAGE, tmdbLanguage);
        uriBuilder.addParameter(API_KEY, tmdbApiKey);
        // todo implement joining ids in string.
        uriBuilder.addParameter(FAVORITE_ACTORS, null);
        uriBuilder.addParameter(PRIMARY_RELEASE_YEAR, primaryReleaseYear);
        return uriBuilder.build().toString();
    }
}
