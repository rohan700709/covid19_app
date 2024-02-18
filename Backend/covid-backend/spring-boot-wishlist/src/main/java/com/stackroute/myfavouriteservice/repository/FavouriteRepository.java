//completed---fully same
package com.stackroute.myfavouriteservice.repository;

import com.stackroute.myfavouriteservice.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavouriteRepository extends MongoRepository<User, String> {
	
	public User findByEmail(String email);

}
