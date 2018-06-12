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


@Service
public class TmdbAPIServiceImpl implements TmdbAPIService {

    @Value("${tmdb.v3.apikey}")
    private String tmdbApiKey;

    @Value("${tmdb.language}")
    private String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Override
    public String retrieveMovies(String path) throws IOException {
        try {
            String url = createTmdbUrl(path);
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

    private String createTmdbUrl(String s) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + s);
        uriBuilder.addParameter("language", tmdbLanguage);
        uriBuilder.addParameter("api_key", tmdbApiKey);
        uriBuilder.addParameter("primary_release_year", "2010");
        return uriBuilder.build().toString();
    }
}
