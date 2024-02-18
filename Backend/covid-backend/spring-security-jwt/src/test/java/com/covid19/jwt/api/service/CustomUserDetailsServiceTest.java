package com.covid19.jwt.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.covid19.jwt.api.entity.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomUserDetailsServiceTest {

	 @Autowired
	    CustomUserDetailsService customUserDetailsService;

 
	 @Test
	 public void userRegistration() throws Exception {
		 User u=new User("test2","password","testmail2@gmail.com");
		 Boolean exist = customUserDetailsService.userRegistration(u);
		 assertTrue(exist.equals(true));
	 }
	 
//	 @Test
//	 public void userRegistration() throws Exception {
//		 User u=new User("test2","password","testmail2@gmail.com");
//		 Boolean exist = customUserDetailsService.userRegistration(u);
//		 assertTrue(exist.equals(true));
//	 }
//	 
//	 @Test
//	 public void userRegistration() throws Exception {
//		 User u=new User("test2","password","testmail2@gmail.com");
//		 Boolean exist = customUserDetailsService.userRegistration(u);
//		 assertTrue(exist.equals(true));
//	 }
 
}
