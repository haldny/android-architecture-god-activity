package com.example.vhra.galeria.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Media {

    private String mThumbnailLocalPath;

    private static final List<String> FILE_EXTENSIONS =  Arrays.asList("jpg", "jpeg", "png");


    public Media(String thumbnail) {
        mThumbnailLocalPath = thumbnail;
    }

    public String getThumbnailLocalPath() {
        return mThumbnailLocalPath;
    }


    public static List<Media> findAll() {
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
