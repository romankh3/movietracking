package ua.romankh3.movie.tracking.service.impl;

import org.springframework.stereotype.Service;
import ua.romankh3.movie.tracking.service.HttpURLConnectorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class HttpURLConnectorServiceImpl implements HttpURLConnectorService {

    private static final String MOVIE_SITE = "https://www.themoviedb.org";

    private static final String SITE_PART = "/discover/" +
            "movie?primary_release_date.gte=2014-09-15&" +
                  "primary_release_date.lte=2014-10-22";


    @Override
    public String sendGET() throws IOException {
        URL obj = new URL(MOVIE_SITE + SITE_PART);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        return null;
    }
}
