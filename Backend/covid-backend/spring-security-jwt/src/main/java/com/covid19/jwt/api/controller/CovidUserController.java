package com.covid19.jwt.api.controller;

import com.covid19.jwt.api.entity.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.covid19.jwt.api.entity.AuthRequest;
import com.covid19.jwt.api.entity.User;
import com.covid19.jwt.api.repository.UserRepository;
import com.covid19.jwt.api.service.CustomUserDetailsService;
import com.covid19.jwt.api.util.JwtUtil;




@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/auth")
public class CovidUserController
{
    
    

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository repository;

    @PostMapping("/userRegistration")
    public User userRegistration(@RequestBody User userDetail) throws Exception {
    	
    	User user;
        //UserResponse userResponse = new UserResponse();
		try {
            if(repository.findByEmail(userDetail.getEmail()) != null) {
                throw new RuntimeException("User already registered");
            }
			user = repository.save(userDetail);
			
			//return ResponseEntity.ok("Registration successful");
		} catch (Exception e) {
			throw new Exception("Duplicate email Id");
		}
		return user;
    }

    @PostMapping("/userUpdate")
    public User userUpdate(@RequestBody User userDetail) throws Exception {
    	
    	User user;
		try {
			user = repository.save(userDetail);
			
			//return ResponseEntity.ok("Registration successful");
		} catch (Exception e) {
			throw new Exception("Duplicate email Id");
		}
    	
		return user;
    }
    
    @PostMapping("/authenticate")
    public UserResponse generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        UserResponse userResponse = new UserResponse();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid email/password");
        }
        String token = jwtUtil.generateToken(authRequest.getEmail());

        try {

            userResponse.setToken(token);
            User user = repository.findByEmail(authRequest.getEmail());
            userResponse.setUserName(user.getUserName());
            userResponse.setEmail(user.getEmail());
        }catch (Exception e){
            throw new Exception("Token not provided");
        }
//        return userResponse ;
        return userResponse;
    }
    
    
    
    @RequestMapping("/hello")
	public String testHandler() {
		String text="hello this is home page";
		return text;
	}




}
