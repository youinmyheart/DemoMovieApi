package com.demo.movieapi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.R;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.viewmodel.MovieReviewViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieReviewRecyclerViewAdapter extends RecyclerView.Adapter<MovieReviewRecyclerViewAdapter.MovieReviewViewHolder> {

    private static final String TAG = MovieReviewRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<MovieReview.ReviewDetail> reviewList;
    private MovieReviewViewModel viewModel;

    public MovieReviewRecyclerViewAdapter(Context context, List<MovieReview.ReviewDetail> reviewList, MovieReviewViewModel viewModel) {
        this.context = context;
        this.reviewList = reviewList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_movie, parent, false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        MovieReview.ReviewDetail review = reviewList.get(position);
        String imagePath = viewModel.getAvatarPath(review);
        Log.d(TAG, "imagePath: " + imagePath);
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(holder.imvAvatar, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError");
            }
        });

        holder.tvAuthor.setText(review.getAuthor());
        holder.tvContent.setText(review.getContent());
        holder.tvMoviePoint.setText(viewModel.getMoviePoint(review));
        holder.tvTimeAgo.setText(viewModel.getTimeAgo(review));
    }

    @Override
    public int getItemCount() {
        // show maximum 3 reviews
        return Math.min(reviewList.size(), 3);
    }

    public class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        ImageView imvAvatar;
        TextView tvAuthor;
        TextView tvContent;
        LinearLayout containerRateStar;
        TextView tvMoviePoint;
        ImageView imvStar1;
        ImageView imvStar2;
        ImageView imvStar3;
        ImageView imvStar4;
        ImageView imvStar5;
        TextView tvTimeAgo;

        public MovieReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvatar = itemView.findViewById(R.id.imvAvatar);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvContent = itemView.findViewById(R.id.tv_content);
            containerRateStar = itemView.findViewById(R.id.container_rate_star);
            tvMoviePoint = itemView.findViewById(R.id.tv_movie_point);
            imvStar1 = itemView.findViewById(R.id.imv_star_1);
            imvStar2 = itemView.findViewById(R.id.imv_star_2);
            imvStar3 = itemView.findViewById(R.id.imv_star_3);
            imvStar4 = itemView.findViewById(R.id.imv_star_4);
            imvStar5 = itemView.findViewById(R.id.imv_star_5);
            tvTimeAgo = itemView.findViewById(R.id.tv_time_ago);
        }
    }
}
