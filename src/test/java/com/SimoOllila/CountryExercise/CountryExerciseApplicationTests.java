package com.SimoOllila.CountryExercise;

import com.SimoOllila.CountryExercise.Models.Country;
import com.SimoOllila.CountryExercise.Services.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
@SpringBootTest
class CountryExerciseApplicationTests {

	@Test
	void contextLoads() {
		CountryService countryService = new CountryService();

		ArrayList<Country> countries = countryService.getCountryByName("Finland");
		if (countries.size() < 1) {
			System.out.println("No country found");
		}
	}

}
