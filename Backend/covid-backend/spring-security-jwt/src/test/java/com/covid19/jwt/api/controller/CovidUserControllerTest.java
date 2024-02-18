package com.covid19.jwt.api.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import com.covid19.jwt.api.entity.User;
import com.covid19.jwt.api.repository.UserRepository;
import com.covid19.jwt.api.service.CustomUserDetailsService;
import com.covid19.jwt.api.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CovidUserController.class)
public class CovidUserControllerTest {
//    @Autowired
//    private transient MockMvc mockMvc;
//
//    @MockBean
//    private transient CustomUserDetailsService service;
//
//    @MockBean
//    private JwtUtil securityTokenGenerator;
//
//    private transient User user;
//
//    @InjectMocks
//    private CovidUserController controller;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        user = new User("test", "123456", "test@gmail.com");
//    }

    @Mock
    private UserRepository userRepository;
    private User user;

    Optional<User> optional;

    @InjectMocks
    CustomUserDetailsService userServiceImpl;

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserName("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");
        optional = Optional.of(user);

    }
    @AfterEach
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }
    @Test
    public void UserRegister() throws Exception{
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(true, userServiceImpl.userRegistration(user));
    }

    @Test
    public void ExistsUserRegistration() {
        Mockito.when(userRepository.findByUserName("test")).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(Exception.class,
                ()->userServiceImpl.userRegistration(user));
    }

    @Test
    public void ExistLoginUser() throws UsernameNotFoundException {
        Mockito.when(userRepository.findByUserName("test")).thenReturn(user);
        User fetchedUser = new User("test","password","test@gmail.com");
        assertEquals("test", fetchedUser.getUserName());
    }



}
