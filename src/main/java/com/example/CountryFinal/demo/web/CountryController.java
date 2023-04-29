package com.example.CountryFinal.demo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CountryFinal.demo.domain.*;
import com.example.CountryFinal.demo.domain.Country;
import com.example.CountryFinal.demo.domain.CountryRepository;
import com.example.CountryFinal.demo.domain.User;

@Controller
public class CountryController {

	// Maps to the "/index" endpoint and returns the "index" page
	@GetMapping("/")
	public String startIndex() {
		return "login";
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CountryRepository cRepository;

	// Maps to the "/countryList" endpoint and returns the "countryList" page
	@GetMapping("/countryList")
	public String countryList(Model model) {

		// Add the necessary attributes to the model
		model.addAttribute("country", new Country());
		model.addAttribute("countries", cRepository.findAll());

		// Return the "countryList" page
		return "countryList";
	}

	// Maps to the "/addcountry" endpoint and returns the "addCountry" page
	@GetMapping("/addcountry")
	public String addCountry(Model model) {
		model.addAttribute("country", new Country());

		return "addCountry";
	}

	// Maps to the "/savecountry" endpoint and saves a new country
	@PostMapping("/savecountry")
	public String saveCountry(Country country) {
		System.out.println(country.getId());

		cRepository.save(country);

		return "redirect:countryList";
	}

	// Maps to the "/deletecountry/{id}" endpoint and deletes a country
	@GetMapping("/deletecountry/{id}")
	public String deleteCountry(@PathVariable("id") Long CountryId, Model model) {
		cRepository.deleteById(CountryId);
		return "redirect:../countryList";
	}

	// Maps to the "/editcountry/{id}" endpoint and returns the "editCountry" page
	@GetMapping("/editcountry/{id}")
	public String editCountry(@PathVariable("id") Long CountryId, Model model) {
		model.addAttribute("country", cRepository.findById(CountryId));

		return "editCountry";
	}

	// Maps to the "/login" endpoint and returns the "login" page
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	//searches for specific country inserted in search
	@GetMapping("/countries")
	public String showCountryList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
	    List<Country> countries;
	    if (searchQuery != null && !searchQuery.isEmpty()) {
	        countries = cRepository.findByNameContainingIgnoreCase(searchQuery);
	    } else {
	        countries = (List<Country>) cRepository.findAll();
	    }
	    model.addAttribute("countries", countries);
	    return "countrylist";
	}
	// Maps to the "/userList" endpoint and returns the "users" page
	@GetMapping("/userList")
	public String userList(Model model) {

		// Add the necessary attributes to the model
		model.addAttribute("users", userRepository.findAll());

		// Return the "users" page
		return "users";
	}

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        userRepository.delete(user);

        model.addAttribute("successMessage", "User has been deleted successfully.");

        return "redirect:/userList";

    }
}