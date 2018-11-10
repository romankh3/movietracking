package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ActorCastTMDB {

    @JsonProperty("cast_id")
    private Integer castId;

    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("id")
    private Integer actorId;

    @JsonProperty("name")
    private String fullActorName;

    @JsonProperty("order")
    private Integer order;

    @JsonProperty("profile_path")
    private String profilePath;
}
