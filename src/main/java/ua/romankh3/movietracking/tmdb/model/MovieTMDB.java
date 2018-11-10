package ua.romankh3.movietracking.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class MovieTMDB {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("imdb_id")
    private String ibdmId;

    @JsonProperty("spoken_languages")
    private List<LanguageTMDB> spokenLanguages;

    /**
     * Git picture for background.
     */
    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("revenue")
    private Integer revenue;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty(value = "video")
    private String video;

    @JsonProperty("title")
    private String title;

    @JsonProperty("production_countries")
    private List<ProductionCountryTMDB> productionCountries;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    @JsonProperty("genres")
    private List<GenreTMDB> genres;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private BigDecimal voteAverage;

    @JsonProperty("popularity")
    private BigDecimal popularity;

    @JsonProperty("adult")
    private Boolean adult;

    /**
     * Good for short description.
     */
    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("belongs_to_collection")
    private Object belongsToCollection;

    @JsonProperty("budget")
    private Integer budget;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("vote_count")
    private Integer voteCount;

    //todo use enum Allowed Values: Rumored, Planned, In Production, Post Production, Released, Canceled
    @JsonProperty("status")
    private String status;
}
