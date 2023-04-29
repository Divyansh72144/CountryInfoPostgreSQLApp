package com.example.CountryFinal.demo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//Declare an interface for the User repository, which extends the CrudRepository interface
public interface UserRepository extends CrudRepository<User, Long> {
	
    // Declare a custom query method to find a user by username
	User findByUsername(String username);
    List<User> findAll();
}