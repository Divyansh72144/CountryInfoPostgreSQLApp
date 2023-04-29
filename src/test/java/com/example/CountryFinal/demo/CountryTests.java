package com.example.CountryFinal.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.CountryFinal.demo.domain.Country;
import com.example.CountryFinal.demo.domain.CountryRepository;

@SpringBootTest
public class CountryTests {

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void testReadCountry() {
        Country country = new Country("Australia", "Oceania", 1901, "AU", 25499884);
        Assertions.assertEquals("Australia", country.getname());
        Assertions.assertEquals("Oceania", country.getcontinent());
        Assertions.assertEquals(1901, country.getFoundedYear());
        Assertions.assertEquals("AU", country.getcountryCode());
        Assertions.assertEquals(25499884, country.getpopulation());
    }
    @Test
    public void testUpdateCountry() {
        Country country = new Country("Mexico", "North America", 1821, "MX", 126200000);
        countryRepository.save(country);

        country.setpopulation(130000000);
        countryRepository.save(country);

        Country updatedCountry = countryRepository.findById(country.getId()).orElse(null);

        assertNotNull(updatedCountry);
        assertEquals(130000000, updatedCountry.getpopulation());
    }
    @Test
    public void testDeleteCountry() {
        Country country = new Country("Brazil", "South America", 1822, "BR", 212600000);
        countryRepository.save(country);

        countryRepository.deleteById(country.getId());

        Country deletedCountry = countryRepository.findById(country.getId()).orElse(null);

        assertNull(deletedCountry);
    }
    @Test
    public void testCreateCountry() {
        Country country = new Country("USA", "North America", 1776, "US", 328200000);
        countryRepository.save(country);

        assertNotNull(country.getId());
    }

   

}
