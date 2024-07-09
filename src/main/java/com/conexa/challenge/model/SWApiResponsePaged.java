package com.conexa.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import static com.conexa.challenge.util.ReplaceUrlUtil.replaceBaseUrl;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SWApiResponsePaged<T> {

    private String message;

    @JsonProperty("total_records")
    private int totalRecords;

    @JsonProperty("total_pages")
    private int totalPages;

    private String previous;

    private String next;

    private List<T> results;

    public void replaceUrl() {
        this.previous = replaceBaseUrl(this.previous);
        this.next = replaceBaseUrl(this.next);
    }
}
