package com.stackroute.myfavouriteservice.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.myfavouriteservice.exception.CountryAlreadyExistsException;
import com.stackroute.myfavouriteservice.exception.CountryNotFoundException;
import com.stackroute.myfavouriteservice.model.Covid;
import com.stackroute.myfavouriteservice.model.User;
import com.stackroute.myfavouriteservice.repository.FavouriteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import junit.framework.Assert;

public class FavouriteServiceTest {
    @Mock
    private FavouriteRepository favRepository;

    @InjectMocks
    public FavouriteServiceImpl favouriteServiceImpl;

    private User user;

    private Covid covid;

    private List list;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        covid = new Covid();

        covid.setCountry("India");
        covid.setId("99");
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
    public void savePlayerToFavoriteListTest() throws CountryAlreadyExistsException {

        user = new User();
        user.setEmail("test@gmail.com");

        Mockito.when(favRepository.findByEmail(user.getEmail())).thenReturn(user);
        User fetchedUser = favouriteServiceImpl.saveCountryToFavorite(covid, user.getEmail());
        Assert.assertEquals(fetchedUser, user);
        verify(favRepository, timeout(1)).findByEmail(user.getEmail());
        verify(favRepository, times(1)).save(user);
    }

    @Test
    public void deletePlayerFromFavoriteListTest() throws CountryNotFoundException {
        Mockito.when(favRepository.findByEmail(user.getEmail())).thenReturn(user);
        User fetchedUser = favouriteServiceImpl.deleteCountryFromFavorite(covid.getCountry(),user.getEmail());
        Assert.assertEquals(fetchedUser, user);
        verify(favRepository, timeout(1)).findByEmail(user.getEmail());
        verify(favRepository, times(1)).save(user);

    }

    @Test
    public void testgetAllFavoriteList() throws Exception {
        Mockito.when(favRepository.findByEmail(user.getEmail())).thenReturn(user);
        List fetchedList = favouriteServiceImpl.getCountryList(user.getEmail());
        Assert.assertEquals(fetchedList, list);
        verify(favRepository, times(1)).findByEmail(user.getEmail());

    }


}
