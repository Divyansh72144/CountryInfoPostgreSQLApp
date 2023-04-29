//package declaration
package com.example.CountryFinal.demo;

// These are the imports for Spring Security and Web
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.CountryFinal.demo.web.*;

// configuration class for web security
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // custom implementation of UserDetailsService
    @Autowired
    private UserDetailServiceImpl userDetailsService;	

    // Overriding the configure() method to customize HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Allowing access to CSS resources even when user is not logged in
            .authorizeRequests().antMatchers("/css/**").permitAll()
            .and()
            // Allowing unauthenticated access to /signup and /saveuser URLs
            .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
            .and()
            // Requiring authentication for all other requests
            .authorizeRequests().anyRequest().authenticated()
            .and()
            // Configuring form-based login
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/countryList", true)
                .permitAll()
                .and()
            // Configuring logout
            .logout()
                .permitAll();
    }
    
    // Overriding the configureGlobal() method to customize AuthenticationManagerBuilder
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Specifying UserDetailsService implementation and password encoder
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
