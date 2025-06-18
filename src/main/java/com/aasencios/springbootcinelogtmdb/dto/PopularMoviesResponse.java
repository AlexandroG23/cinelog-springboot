package com.aasencios.springbootcinelogtmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PopularMoviesResponse {

    private int page;
    private List<Movie> results;

    @JsonProperty("total_pages")
    private int totalPages;

    // Getters and Setters

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
