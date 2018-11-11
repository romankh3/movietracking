package ua.romankh3.movietracking.tmdb;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;

import static ua.romankh3.movietracking.tmdb.service.impl.TmdbDiscoverServiceImpl.TMDBConstants.API_KEY;
import static ua.romankh3.movietracking.tmdb.service.impl.TmdbDiscoverServiceImpl.TMDBConstants.LANGUAGE;

public class Utils {

    @Value("${tmdb.v3.apikey}")
    private static String tmdbApiKey;

    @Value("${tmdb.language}")
    private static String tmdbLanguage;

    @Value("${tmdb.api.base.url}")
    private static String tmdbApiBaseUrl;

    public static String createDefaultTmdbUrlBuilder(String path) {
        try {
            URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + path);
            uriBuilder.addParameter(LANGUAGE, tmdbLanguage);
            uriBuilder.addParameter(API_KEY, tmdbApiKey);
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
