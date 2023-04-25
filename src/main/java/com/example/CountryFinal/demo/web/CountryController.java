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
    @GetMapping("/index")
    public String startIndex() {
        return "index";
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    CountryRepository cRepository;

    @GetMapping("/countryList")
    public String countryList(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	User user = userRepository.findByUsername(username);
    	List<Country> countries = cRepository.findByUser(user);
        List<Country> userCountries = cRepository.findByUser(user);
        model.addAttribute("country", new Country());
        model.addAttribute("countries", cRepository.findAll());
        model.addAttribute("userCountries", userCountries);
        return "countryList";
    }
    @GetMapping("/addcountry")
	public String addCountry(Model model) {
		model.addAttribute("country", new Country());
		
		return "addCountry";
	}

	@PostMapping("/savecountry")
	public String saveCountry(Country country) {
		System.out.println(country.getId());

		cRepository.save(country);

		return "redirect:countryList";
	}

	@GetMapping("/deletecountry/{id}")
	public String deleteCountry(@PathVariable("id") Long CountryId, Model model) {
		cRepository.deleteById(CountryId);
		return "redirect:../countryList";
	}

	@GetMapping("/editcountry/{id}")
	public String editCountry(@PathVariable("id") Long CountryId, Model model) {
		model.addAttribute("country", cRepository.findById(CountryId));
		
		return "editCountry";
	}  
	    
	    @RequestMapping("/login")
	    public String login() {
	        return "login";
	    }
	    @GetMapping("/countries")
	    public String showCountryList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
	      List<Country> countries;
	      if (searchQuery != null && !searchQuery.isEmpty()) {
	        countries = cRepository.findByNameContaining(searchQuery);
	      } else {
	        countries = (List<Country>) cRepository.findAll();
	      }
	      model.addAttribute("countries", countries);
	      return "countrylist";
	    }

	   
	 
	   
}