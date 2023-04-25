
package com.example.CountryFinal.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String continent;
    private int FoundedYear;
    private String countryCode;
    private int population;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Country() {

    }

    public Country(String name, String continent, int FoundedYear, String countryCode, int population) {
        super();
        this.name = name;
        this.continent = continent;
        this.FoundedYear = FoundedYear;
        this.countryCode = countryCode;
        this.population = population;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getcontinent() {
        return continent;
    }

    public void setcontinent(String continent) {
        this.continent = continent;
    }

    public int getFoundedYear() {
        return FoundedYear;
    }

    public void setFoundedYear(int FoundedYear) {
        this.FoundedYear = FoundedYear;
    }

    public String getcountryCode() {
        return countryCode;
    }

    public void setcountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getpopulation() {
        return population;
    }

    public void setpopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.name + ", " + this.continent + ", " + this.countryCode + ", " + this.FoundedYear + ", "
                + this.population + " EUR";
    }
}
