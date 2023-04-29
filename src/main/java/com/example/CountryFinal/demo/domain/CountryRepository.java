package com.example.CountryFinal.demo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//Declare an interface for the Country repository, which extends the CrudRepository interface
public interface CountryRepository extends CrudRepository<Country, Long> {
    // Declare a custom query method to find countries by user
	  List<Country> findByUser(User user);
	// Declare a custom query method to find countries by name containing a given string
	    List<Country> findByNameContainingIgnoreCase(String name);	  
	}

