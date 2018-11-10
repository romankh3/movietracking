package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LanguageTMDB {

    @JsonProperty("name")
    private String name;

    @JsonProperty("iso_639_1")
    private String ido_639_1;
}
