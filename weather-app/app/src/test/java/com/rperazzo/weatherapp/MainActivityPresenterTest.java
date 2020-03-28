package com.rperazzo.weatherapp;

import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.presenter.MainActivityPresenter;
import com.rperazzo.weatherapp.presenter.MainActivityPresenterImpl;
import com.rperazzo.weatherapp.view.MainActivity;
import com.rperazzo.weatherapp.view.MainActivityView;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;


import java.util.ArrayList;

public class MainActivityPresenterTest {

    @Mock
    private MainActivityView view;

    private MainActivityPresenter presenter;

    @Before
    public void setup() {
        this.view = Mockito.mock(MainActivity.class);
        this.presenter = new MainActivityPresenterImpl(view);
    }

    @Test
    public void givenPresenterInitialize_whenCallOnCreate_shouldSetupAdapter() {
        //initialize tests scenery
        ArrayList<City> cities = new ArrayList<>();

        //expected action
        presenter.onCreate();

        //result test
        Mockito.verify(view).setupAdapter(cities);
    }
}
