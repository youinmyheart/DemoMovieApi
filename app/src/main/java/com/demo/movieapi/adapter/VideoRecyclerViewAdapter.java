package com.demo.movieapi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.R;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.repository.APIManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoViewHolder> {

    private static final String TAG = VideoRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<MovieDetail.Video> videoList;

    public VideoRecyclerViewAdapter(Context context, List<MovieDetail.Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position);
        String imagePath = APIManager.YOUTUBE_IMAGE_BASE_URL + videoList.get(position).getKey() + "/mqdefault.jpg";
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(holder.imvThumbnailVideo, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError");
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView imvThumbnailVideo;
        ImageView imvPlay;
        View viewShadow;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumbnailVideo = itemView.findViewById(R.id.imvThumbnailVideo);
            imvPlay = itemView.findViewById(R.id.imvPlay);
            viewShadow = itemView.findViewById(R.id.view_shadow);
            viewShadow.setAlpha(0.1f);
        }
    }
}
