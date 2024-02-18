package com.stackroute.myfavouriteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Country already exists")
public class CountryAlreadyExistsException extends Exception {

}
