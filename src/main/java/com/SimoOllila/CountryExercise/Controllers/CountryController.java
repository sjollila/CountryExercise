package com.SimoOllila.CountryExercise.Controllers;
import com.SimoOllila.CountryExercise.DTOs.CountriesRoot;
import com.SimoOllila.CountryExercise.DTOs.CountryBaseDto;
import com.SimoOllila.CountryExercise.DTOs.SingleCountryBaseDto;
import com.SimoOllila.CountryExercise.Models.Country;
import com.SimoOllila.CountryExercise.Services.CountryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * Rest API
 * Available services
 * CountryMapperAll             Returns all countries retrieved from the country service
 * CountryMapperSingle          Returns the country, which name was given. If nothing found, no class is returned
 */
@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @Mapper
    public interface CountryMapperAll {
        CountryMapperAll INSTANCE = Mappers.getMapper(CountryMapperAll.class);

        @Mapping(source = "name.common", target = "name")
        @Mapping(source = "cca2", target = "countryCode")
        CountryBaseDto convert(Country country);
        ArrayList<CountryBaseDto> convert(ArrayList<Country>  countries);
    }
    @Mapper
    public interface CountryMapperSingle {
        CountryMapperSingle INSTANCE = Mappers.getMapper(CountryMapperSingle.class);
        @Mapping(source = "name.common", target = "name")
        @Mapping(source = "cca2", target = "countryCode")
        @Mapping(expression = "java(country.getCapital().get(0))", target = "capital")
        @Mapping(source = "population", target = "population")
        @Mapping(source = "flags.svg", target = "flagFileUrl")
        SingleCountryBaseDto convert(Country country);
        ArrayList<SingleCountryBaseDto> convert(ArrayList<Country>  countries);
    }

    @GetMapping("/countries")
    public CountriesRoot getCountries()
    {
        CountriesRoot countriesRoot = new CountriesRoot();
        countriesRoot.setCountries(CountryMapperAll.INSTANCE.convert(countryService.getAllCountries()));
        return countriesRoot;
    }

    @RequestMapping("/countries/{name}")
    public ArrayList<SingleCountryBaseDto> getCountry(@PathVariable(value="name") String country) {
        return CountryMapperSingle.INSTANCE.convert(countryService.getCountryByName(country));
    }
}
