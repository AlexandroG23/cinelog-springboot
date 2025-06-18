package com.aasencios.springbootcinelogtmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;

import java.util.List;
import java.util.stream.Collectors;

public class Movie {

    private Long id;
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    private String youtubeTrailerKey;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @Transient // No se serializa al guardar (si aplicara a una entidad)
    private List<String> genreNames;

    public List<String> getGenreNames() {
        if (genreIds == null) return List.of();
        return genreIds.stream()
                .map(GenreMapper::getGenreName)
                .collect(Collectors.toList());
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getYoutubeTrailerKey() {
        return youtubeTrailerKey;
    }

    public void setYoutubeTrailerKey(String youtubeTrailerKey) {
        this.youtubeTrailerKey = youtubeTrailerKey;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
