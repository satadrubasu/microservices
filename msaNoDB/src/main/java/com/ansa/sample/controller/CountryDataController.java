package com.ansa.sample.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryDataController {
	
	@Autowired
	private Map<String,String> countryList;
	
	@GetMapping(value="/country" , produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> getEmployeeDetails() {
		return countryList;
	}

	@GetMapping(value="/countryCapital/{countryName}" , produces=MediaType.APPLICATION_JSON_VALUE)
	public String getCountryDetails(@PathVariable String countryName) {
		return countryList.get(countryName);
	}
}
