package com.example.vhra.galeria;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.vhra.galeria.adapter.MediasAdapter;
import com.example.vhra.galeria.presenter.IPresenter;
import com.example.vhra.galeria.presenter.Presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class PresenterTest {

    private Presenter presenter;
    private MyActivity activity;

    public class MyActivity implements IPresenter {

        public MediasAdapter adapter = null;

        @Override
        public void setAdapter(MediasAdapter adapter) {
            this.adapter = adapter;
        }

    }

    @Before
    public void before() {
        activity = new MyActivity();
        presenter = new Presenter(activity);
    }

    @After
    public void after() {
        presenter = null;
        activity = null;
    }

    @Test
    public void isAdapterIncluded() {

        presenter.onCreate();
        assertNotNull(activity.adapter);

    }

}