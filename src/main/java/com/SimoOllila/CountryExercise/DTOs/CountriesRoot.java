package com.SimoOllila.CountryExercise.DTOs;

import java.util.ArrayList;

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
