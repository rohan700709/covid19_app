package com.stackroute.myfavouriteservice.controller;

import java.util.List;

import com.stackroute.myfavouriteservice.exception.CountryAlreadyExistsException;
import com.stackroute.myfavouriteservice.exception.CountryNotFoundException;
import com.stackroute.myfavouriteservice.model.Covid;
import com.stackroute.myfavouriteservice.model.User;
import com.stackroute.myfavouriteservice.service.FavouriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/watchlist")
public class FavoriteController {
	
	private ResponseEntity responseEntity;

	private FavouriteService favService;

	public FavoriteController() {
		super();
	}

	@Autowired
	public FavoriteController(FavouriteService favService) {
		super();
		this.favService = favService;
	}
	
	
	@PostMapping("/{email}/country")
	public ResponseEntity<?> addCountryToFavoriteList(@RequestBody Covid covid,
			@PathVariable String email) throws CountryAlreadyExistsException {

	
		if(email!=null) {
			try {
				User user1 = favService.saveCountryToFavorite(covid, email);
			//	System.out.println(user1);
				if(user1.getEmail()!=null) {
					responseEntity = new ResponseEntity<User>(user1, HttpStatus.CREATED);
				}

				else {
					System.out.println("email not found...maybe its null");
				}
				
			} catch (CountryAlreadyExistsException e) {
				throw new CountryAlreadyExistsException();
			} catch (Exception e) {
				responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

			}
			return responseEntity;
			
		}
		else {
			return null;
		}

		

	}
	
	@DeleteMapping("/{email}/{country}")
	public ResponseEntity<?> deleteFromList(@PathVariable String country, @PathVariable String email)
			throws CountryNotFoundException {

		try {
			User user1 = favService.deleteCountryFromFavorite(country, email);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
		} catch (CountryNotFoundException e) {
			throw new CountryNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}
	
	@GetMapping("/{email}/country")
	public ResponseEntity<?> getCountryList(@PathVariable String email) throws CountryNotFoundException {

		try {
			List<Covid> countryList = favService.getCountryList(email);
			responseEntity = new ResponseEntity(countryList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}


}




