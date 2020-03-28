package com.example.vhra.galeria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vhra.galeria.adapter.MediasAdapter;
import com.example.vhra.galeria.presenter.Presenter;
import com.example.vhra.galeria.presenter.IPresenter;

public class MainActivity extends AppCompatActivity implements IPresenter {

    private Presenter presenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_medias);

        presenter = new Presenter(this);
        presenter.onCreate();
    }

    @Override
    public void setAdapter(MediasAdapter adapter) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
