 
package com.stackroute.myfavouriteservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	
	@Id
	private String email;
	
	
	private List<Covid> covidList;



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	


	public List<Covid> getCountryList() {
		return covidList;
	}


	public void setCountryList(List<Covid> countryList) {
		covidList = countryList;
	}


	@Override
	public String toString() {
		return "User [email=" + email + ", covidList=" + covidList + "]";
	}


	public User(String email, List<Covid> covidList) {
		super();
		this.email = email;
		this.covidList = covidList;
	}


	public User() {
		super();
		//TODO Auto-generated constructor stub
	}

	
}
