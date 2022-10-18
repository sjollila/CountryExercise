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
 *
 * @author Adam
 */
@Service
public class CountryService {

    private final CountryHttpClient countryService = new CountryHttpClient();

    private ArrayList<Country> useService(String path) {
        return countryService.getResource(path);
    }


    public ArrayList<Country> getAllCountries() {
        return useService("all");
    }

    public ArrayList<Country> getCountryByName(String name) {
        return useService("name/" + name);
    }

    public ArrayList<Country> getCountryByFullName(String name) {
        return useService("name/" + name + "?fulltext=true");
    }

    public ArrayList<Country> getCountryByCode(String code) {
        return useService("alpha/" + code);
    }

    public ArrayList<Country> getCountryByCodeList(String codeList) {
        return useService("alpha?codes=" + codeList);
    }

    public ArrayList<Country> getCountryByCurrency(String currency) {
        return useService("currency/" + currency);
    }

    public ArrayList<Country> getCountryByLanguage(String lang) {
        return useService("lang/" + lang);
    }

    public ArrayList<Country> getCountryByTranslation(String translation) {
        return useService("translation/" + translation);
    }

    public ArrayList<Country> getCountryByCapitalCity(String capital) {
        return useService("capital/" + capital);
    }

    public ArrayList<Country> getCountryByRegion(String region) {
        return useService("region/" + region);
    }

    public ArrayList<Country> getCountryBySubRegion(String subregion) {
        return useService("subregion/" + subregion);
    }

    public ArrayList<Country> getCountryByDemonym(String demonym) {
        return useService("demonym/" + demonym);
    }
}
