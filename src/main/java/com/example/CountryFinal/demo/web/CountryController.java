package com.example.CountryFinal.demo.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.CountryFinal.demo.domain.*;
import com.example.CountryFinal.demo.domain.Country;
import com.example.CountryFinal.demo.domain.CountryRepository;

@Controller
public class CountryController {
    @GetMapping("/index")
    public String startIndex() {
        return "index";
    }

    @Autowired
    CountryRepository cRepository;

    @GetMapping("/countryList")
    public String countryList(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("countries", cRepository.findAll());
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
	    
	    @RequestMapping("/")
	    public String login() {
	        return "login";
	    }
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	   

	    @GetMapping("/signup")
	    public String showSignUpForm(Model model) {
	        model.addAttribute("signupform", new SignUpForm());
	        return "signup";
	    }

	   
}