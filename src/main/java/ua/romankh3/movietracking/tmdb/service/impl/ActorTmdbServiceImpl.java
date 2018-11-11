package ua.romankh3.movietracking.tmdb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.romankh3.movietracking.model.Actor;
import ua.romankh3.movietracking.tmdb.Utils;
import ua.romankh3.movietracking.tmdb.model.ActorCastTMDB;
import ua.romankh3.movietracking.tmdb.model.ActorTMDB;
import ua.romankh3.movietracking.tmdb.service.ActorTmdbService;
import ua.romankh3.movietracking.tmdb.service.TmdbDiscoverService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.romankh3.movietracking.tmdb.Utils.createDefaultTmdbUrlBuilder;
import static ua.romankh3.movietracking.tmdb.service.impl.TmdbDiscoverServiceImpl.TMDBConstants.API_KEY;
import static ua.romankh3.movietracking.tmdb.service.impl.TmdbDiscoverServiceImpl.TMDBConstants.LANGUAGE;

@Service
public class ActorTmdbServiceImpl implements ActorTmdbService {

    private final String pathToImage = "https://image.tmdb.org/t/p/original";

    @Override
    public List<Actor> findActorsByMovie(Integer movieUd) {
        String path = "/movie/" + movieUd + "/credits";
        List<ActorCastTMDB> castByMovie = findCastByMovie(path);
        return castByMovie.stream()
                .map(this::toActor)
                .collect(Collectors.toList());
    }

    @Override
    public ActorTMDB findByActorId(Integer actor_id) {
        String path = "/person/" + actor_id;
        return null;
    }

    private Actor toActor(ActorCastTMDB castTMDB) {
        Actor actor = new Actor();
        actor.setActive(1);
        actor.setFullName(castTMDB.getFullActorName());
        actor.setThdbId(castTMDB.getActorId());
        if(castTMDB.getProfilePath() != null) {
            actor.setPicturePath(castTMDB.getProfilePath());
        } else {
            actor.setPicturePath("/images/no-image-avaliable.jpg");
        }

        actor.setPicturePath(castTMDB.getProfilePath());
        return actor;
    }

    private List<ActorCastTMDB> findCastByMovie(String path) {
        try {
            String url = createDefaultTmdbUrlBuilder(path);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

            if(jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }
            String jsonList = jsonResponse.getBody().getObject().get("cast").toString();
            return new ObjectMapper().readValue(jsonList, new TypeReference<List<ActorCastTMDB>>(){});
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
