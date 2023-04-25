package com.example.CountryFinal.demo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
	  List<Country> findByUser(User user);
	  List<Country> findByNameContaining(String name);
	  
	}

