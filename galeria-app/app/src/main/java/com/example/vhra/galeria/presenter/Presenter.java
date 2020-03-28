package com.example.vhra.galeria.presenter;

import com.example.vhra.galeria.adapter.MediasAdapter;
import com.example.vhra.galeria.model.Media;
import java.util.List;

public class Presenter {

    private PresenterView view;

    private List<Media> medias;

    public Presenter(PresenterView view) {
        this.view = view;
    }

    public void onCreate() {
        this.medias = Media.findAll();

        if (view != null)
            view.setAdapter(new MediasAdapter( medias ));
    }

    public void onDestroy() {
        view = null;
    }

}