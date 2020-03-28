package com.example.vhra.galeria.presenter;

import com.example.vhra.galeria.view.adapter.MediasAdapter;
import com.example.vhra.galeria.model.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Presenter {

    private IPresenter view;

    private List<Media> medias;

    private static final List<String> FILE_EXTENSIONS =  Arrays.asList("jpg", "jpeg", "png");

    public Presenter(IPresenter view) {
        this.view = view;
    }

    public void onCreate() {
        this.medias = findAllMedia();

        if (view != null)
            view.setAdapter(new MediasAdapter( medias ));
    }

    public void onDestroy() {
        view = null;
    }


    private List<Media> findAllMedia() {
        List<Media> medias = new ArrayList<>();

        File directory = new File(android.os.Environment.getExternalStorageDirectory() + File.separator  + "Download");
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                String filePath = file.getAbsolutePath();
                if (isSupportedFile(filePath)) {
                    medias.add(new Media(filePath));
                }
            }
        }

        return medias;
    }

    private static boolean isSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1), filePath.length());
        return FILE_EXTENSIONS.contains(ext.toLowerCase(Locale.getDefault()));
    }
}