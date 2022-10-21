package com.SimoOllila.CountryExercise.Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import com.SimoOllila.CountryExercise.Models.*;
import org.springframework.stereotype.Service;

/**
 * This class is what the controller calls to get Country classes for the UI
 * Available services:
 * getAllCountries
 * getCountryByName
 */
@Service
public class CountryService {

    private final CountryHttpClient countryService = new CountryHttpClient();

    private ArrayList<Country> useService(String path) {
        return countryService.getResource(path);
    }

    /**
     *
     * @return ArrayList<Country>
     */
    public ArrayList<Country> getAllCountries() {
        return useService("all");
    }

    /**
     *
     * @param name Name for the country searched. e.g. Finland
     * @return ArrayList<Country>
     */
    public ArrayList<Country> getCountryByName(String name) {
        return useService("name/" + name);
    }
}
