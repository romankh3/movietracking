package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductionCountryTMDB {

    @JsonProperty("iso_3166_1")
    private String iso_3166_1;

    @JsonProperty("name")
    private String name;
}
