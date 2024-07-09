package com.conexa.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import static com.conexa.challenge.util.ReplaceUrlUtil.replaceBaseUrlList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDetail {

    private String model;

    @JsonProperty("vehicle_class")
    private String vehicleClass;

    private String manufacturer;

    @JsonProperty("cost_in_credits")
    private String costInCredits;

    private String length;

    private String crew;

    private String passengers;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

    @JsonProperty("cargo_capacity")
    private String cargoCapacity;

    private String consumables;

    private List<String> films;

    private List<String> pilots;

    private String name;

    public void replaceUrl() {
        this.films = replaceBaseUrlList(this.films);
        this.pilots = replaceBaseUrlList(this.pilots);
    }
}
