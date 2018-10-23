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
import ua.romankh3.movietracking.tmdb.service.MovieTmbdService;
import ua.romankh3.movietracking.tmdb.model.MovieTMDB;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static ua.romankh3.movietracking.tmdb.service.impl.TmdbAPIServiceImpl.TMDBConstants.API_KEY;
import static ua.romankh3.movietracking.tmdb.service.impl.TmdbAPIServiceImpl.TMDBConstants.LANGUAGE;
import static ua.romankh3.movietracking.tmdb.service.impl.TmdbAPIServiceImpl.TMDBConstants.NOW_PLAYING_MOVIE;

@Service
public class MovieTmdbServiceImpl implements MovieTmbdService {

    @Value("${tmdb.v3.apikey}")
    private String tmdbApiKey;

    @Value("${tmdb.language}")
    private String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Override
    public List<MovieTMDB> findNowPlaying(){
        try {
            String uriBuilder = createTmdbUrl(NOW_PLAYING_MOVIE);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(uriBuilder).asJson();

            if(jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }
            String jsonList = jsonResponse.getBody().getObject().get("results").toString();
            ObjectMapper objectMapper = new ObjectMapper();
            List<MovieTMDB> movieTMDBList = objectMapper.readValue(jsonList, new TypeReference<List<MovieTMDB>>(){} );

            return movieTMDBList;
        } catch (URISyntaxException | UnirestException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String createTmdbUrl(String path) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + path);
        uriBuilder.addParameter(LANGUAGE, tmdbLanguage);
        uriBuilder.addParameter(API_KEY, tmdbApiKey);
        return uriBuilder.build().toString();
    }
}
