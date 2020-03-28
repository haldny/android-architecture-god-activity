package com.rperazzo.weatherapp;

import com.rperazzo.weatherapp.entities.City;
import com.rperazzo.weatherapp.entities.Main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PresenterTeste {

    private MyActivity activity;
    private MainActivityPresenter presenter;

    private class MyActivity implements MainActivityPresenter.View {

        public Boolean result = false;

        @Override
        public void searchByName(String name) {
            result = true;
        }
    }

    @Before
    public void before() {
        activity = new MyActivity();
        presenter = new MainActivityPresenter(activity);
    }

    @After
    public void after() {
        presenter = null;
        activity = null;
    }

    @Test
    public void searchByName() {
        presenter.searchByName("Maceio");
        Assert.assertTrue(activity.result);
    }
}
