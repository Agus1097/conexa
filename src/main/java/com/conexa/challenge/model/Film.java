package com.conexa.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import static com.conexa.challenge.util.ReplaceUrlUtil.replaceBaseUrlList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

    private List<String> characters;

    private List<String> starships;

    private List<String> vehicles;

    private String producer;

    private String title;

    @JsonProperty("episode_id")
    private int episodeId;

    private String director;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    public void replaceUrl() {
        this.characters = replaceBaseUrlList(this.characters);
        this.starships = replaceBaseUrlList(this.starships);
        this.vehicles = replaceBaseUrlList(this.vehicles);
    }
}
