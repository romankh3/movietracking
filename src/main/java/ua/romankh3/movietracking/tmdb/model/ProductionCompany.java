package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductionCompany {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("origin_country")
    private String originCountry;
}
