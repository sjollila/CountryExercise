package com.SimoOllila.CountryExercise.DTOs;

public class CountriesRoot {
    CountryBaseDto[] countries;

    // Getter
    public CountryBaseDto[] getName() {
        return countries;
    }

    // Setter
    public void setName(CountryBaseDto[] newCountries) {
        this.countries = newCountries;
    }
}
