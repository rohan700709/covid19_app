
package com.stackroute.myfavouriteservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;

public class Covid {
	
 
	@Id
    private String country;
	
	private String infected;
	private String recovered;
	private String deceased;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfected() {
		return infected;
	}

	public void setInfected(String infected) {
		this.infected = infected;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getDeceased() {
		return deceased;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	

	public Covid(String country, String infected, String recovered, String deceased, String id) {
		super();
		this.country = country;
		this.infected = infected;
		this.recovered = recovered;
		this.deceased = deceased;
		this.id = id;
	}

	public Covid() {
	}
}