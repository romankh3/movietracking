package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActorTMDB {

    @JsonProperty("adult")
    private boolean adult = false;  // PersonPeople info

    @JsonProperty("also_known_as")
    private List<String> alsoKnownAs = new ArrayList<String>();

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("deathday")
    private String deathday;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("place_of_birth")
    private String birthplace;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("popularity")
    private float popularity = 0.0f;

    @JsonProperty("gender")
    private int gender;
}
