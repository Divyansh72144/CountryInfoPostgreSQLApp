package com.example.CountryFinal.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.CountryFinal.demo.domain.Country;
import com.example.CountryFinal.demo.domain.CountryRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CountryRepository cRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Add some sample values to the Country table
    cRepository.save(new Country("United States", "North America", 1776, "US", 328200000));
    	cRepository.save(new Country("Canada", "North America", 1867, "CA", 37742154));
    	cRepository.save(new Country("Brazil", "South America", 1822, "BR", 213993437));
    }
}
