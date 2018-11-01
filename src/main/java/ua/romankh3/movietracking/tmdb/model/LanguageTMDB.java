package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageTMDB {

    @JsonProperty("name")
    private String name;

    @JsonProperty("iso_639_1")
    private String ido_639_1;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdo_639_1() {
        return ido_639_1;
    }

    public void setIdo_639_1(String ido_639_1) {
        this.ido_639_1 = ido_639_1;
    }
}
