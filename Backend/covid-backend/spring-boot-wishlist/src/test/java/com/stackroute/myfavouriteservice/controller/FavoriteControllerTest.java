package com.stackroute.myfavouriteservice.controller;

//import static java.util.Collections.get;
//import static javax.print.attribute.TextSyntax.verify;
import static org.mockito.ArgumentMatchers.eq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.myfavouriteservice.exception.CountryAlreadyExistsException;
import com.stackroute.myfavouriteservice.model.Covid;
import com.stackroute.myfavouriteservice.model.User;
import com.stackroute.myfavouriteservice.service.FavouriteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(FavoriteController.class)
@RunWith(SpringRunner.class)
public class FavoriteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavouriteService favService;

    @InjectMocks
    private FavoriteController favoriteController;

    private User user;

    private Covid covid;

    private List list;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();
        covid = new Covid();

        covid.setCountry("India");
        covid.setId("13");
        list = new ArrayList();
        list.add(covid);

        user = new User();
        user.setEmail("test@gmail.com");
        user.setCountryList(list);
    }

    @After
    public void delete() {
        user = null;
    }

    @Test
    public void testAddCountryToWatchList() throws Exception {
        when(favService.saveCountryToFavorite(any(), eq(user.getEmail()))).thenReturn(user);
        mockMvc.perform(post("/api/v1/watchlist/{email}/country", user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(covid)))
                .andExpect(status().isCreated()).andDo(print());

        verify(favService, times(1)).saveCountryToFavorite(any(), eq(user.getEmail()));
    }

    @Test
    public void testAddCountrToWatchListFailure() throws Exception {
        when(favService.saveCountryToFavorite(any(), eq(user.getEmail())))
                .thenThrow(CountryAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/watchlist/{email}/country", user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(covid)))
                .andExpect(status().isConflict()).andDo(print());

        verify(favService, times(1)).saveCountryToFavorite(any(), eq(user.getEmail()));

    }

    @Test
    public void testGetAllTrackFromWishList() throws Exception {
        when(favService.getCountryList((user.getEmail()))).thenReturn(list);
        mockMvc.perform(get("/api/v1/watchlist/{email}/country", user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(covid))).andExpect(status().isOk())
                .andDo(print());

        verify(favService, times(1)).getCountryList(user.getEmail());

    }

    private static String jsonToString(final Object obj) throws Exception {
        String result;

        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (Exception e) {
            result = "error in Json processing";
        }
        return result;
    }
}
