package com.conexa.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SWApiResponse<T> {

    private String message;

    private SWApiResult<T> result;
}
