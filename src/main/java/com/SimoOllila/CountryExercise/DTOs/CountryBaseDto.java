package com.SimoOllila.CountryExercise.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for service that needs all Country classes
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CountryBaseDto {
    private String name;

    @JsonProperty("country_code")
    private String countryCode;


    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String newName) {
        this.name = newName;
    }

    // Getter
    public String getCountryCode() {
        return countryCode;
    }

    // Setter
    public void setCountryCode(String newCountryCode) {
        this.countryCode = newCountryCode;
    }

}
