package com.demo.movieapi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.OnClickViewHolderItemListener;
import com.demo.movieapi.R;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.APIManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.PopularViewHolder> {
    private static final String TAG = MovieRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<TMDBResponse.Movie> movieList;
    private OnClickViewHolderItemListener listener;

    public MovieRecyclerViewAdapter(Context context, List<TMDBResponse.Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setOnClickItemListener(OnClickViewHolderItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movie, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position);
        TMDBResponse.Movie movie = movieList.get(position);
        String imagePath = APIManager.IMAGE_BASE_URL + "/w300" + movie.getPosterPath();
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(holder.imvMovie, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError");
            }
        });
        holder.tvTitle.setText(movie.getTitle());

        holder.imvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        ImageView imvMovie;
        TextView tvTitle;
        ImageView imvMoreDetail;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            imvMovie = itemView.findViewById(R.id.imv_movie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imvMoreDetail = itemView.findViewById(R.id.imv_more_detail);
        }
    }
}
