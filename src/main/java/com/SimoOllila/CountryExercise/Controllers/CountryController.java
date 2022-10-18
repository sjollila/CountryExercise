package com.SimoOllila.CountryExercise.Controllers;
import com.SimoOllila.CountryExercise.DTOs.CountriesRoot;
import com.SimoOllila.CountryExercise.DTOs.CountryBaseDto;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class CountryController {
    Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService countryService;

    @Mapper
    public interface CountryMapper {
        CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

        @Mapping(source = "name.common", target = "name")
        @Mapping(source = "cca2", target = "countryCode")
        CountryBaseDto convert(Country country);
        ArrayList<CountryBaseDto> convert(ArrayList<Country>  countries);
    }

    @GetMapping("/countries")
    public CountriesRoot getCountries()
    {
        CountriesRoot countriesRoot = new CountriesRoot();
        countriesRoot.setCountries(CountryMapper.INSTANCE.convert(countryService.getAllCountries()));
        return countriesRoot;
    }

    @RequestMapping("/countries/{name}")
    public ArrayList<Country> getCountry(@PathVariable(value="name") String country) {
        logger.info("Get data for "+country);
        return countryService.getCountryByName(country);
    }
}
