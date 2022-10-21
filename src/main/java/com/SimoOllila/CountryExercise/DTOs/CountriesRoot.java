package com.SimoOllila.CountryExercise.DTOs;

import java.util.ArrayList;

/**
 * The root class for all Country classes. This is needed, because the service need to contain all Country classes within a class named countries.
 */
public class CountriesRoot {
    ArrayList<CountryBaseDto> countries;

    // Getter
    public ArrayList<CountryBaseDto> getCountries() {
        return countries;
    }

    // Setter
    public void setCountries(ArrayList<CountryBaseDto> newCountries) {
        this.countries = newCountries;
    }
}
