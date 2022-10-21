package com.SimoOllila.CountryExercise.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO for the service that needs a single Country
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SingleCountryBaseDto {
    private String name;

    @JsonProperty("country_code")
    private String countryCode;
    private String capital;
    private Integer population;
    @JsonProperty("flag_file_url")
    private String flagFileUrl;

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

    public String getCapital() {
        return capital;
    }

    // Setter
//    public void setCapital(List<String> newCapital) {
//        this.capital = newCapital;
//    }
    public void setCapital(String newCapital) {
        this.capital = newCapital;
    }

    // Getter
    public Integer getPopulation() {
        return population;
    }

    // Setter
    public void setPopulation(Integer newPopulation) {
        this.population = newPopulation;
    }

    // Getter
    public String getFlagFileUrl() {
        return flagFileUrl;
    }

    // Setter
    public void setFlagFileUrl(String newFlagFileUrl) {
        this.flagFileUrl = newFlagFileUrl;
    }
}
