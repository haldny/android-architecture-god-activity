package com.example.vhra.galeria.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vhra.galeria.R;
import com.example.vhra.galeria.model.Media;

import java.io.File;
import java.util.List;

public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.MediaViewHolder> {

    private List<Media> mMedias;

    public MediasAdapter(List<Media> mediaList) {
        mMedias = mediaList;
    }

    @NonNull
    @Override
    public MediasAdapter.MediaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_gallery, viewGroup, false);
        return new MediasAdapter.MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediasAdapter.MediaViewHolder mediaViewHolder, int i) {
        Media media = mMedias.get(i);
        mediaViewHolder.imageViewThumbnail.setImageURI(Uri.fromFile(new File(media.getThumbnailLocalPath())));
    }

    @Override
    public int getItemCount() {
        return mMedias != null ? mMedias.size() : 0;
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        public MediaViewHolder(View viewRoot) {
            super(viewRoot);
            imageViewThumbnail = itemView.findViewById(R.id.image_media_thumbnail);
        }
    }
}