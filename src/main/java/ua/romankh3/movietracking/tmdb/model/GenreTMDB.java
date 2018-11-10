package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenreTMDB {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
