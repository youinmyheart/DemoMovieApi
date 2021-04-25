package com.demo.movieapi.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.repository.APIManager;
import com.demo.movieapi.repository.MovieReviewRepo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MovieReviewViewModel extends ViewModel {
    private static final String TAG = MovieReviewViewModel.class.getSimpleName();
    private MovieReviewRepo movieReviewRepo;
    private MutableLiveData<DataWrapper<MovieReview>> mutableLiveData;

    public MovieReviewViewModel() {
        movieReviewRepo = new MovieReviewRepo();
    }

    public MutableLiveData<DataWrapper<MovieReview>> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<DataWrapper<MovieReview>> getMovieReview(int movieId, int page) {
        mutableLiveData = movieReviewRepo.getReviews(movieId, page);
        return mutableLiveData;
    }

    public String getAvatarPath(MovieReview.ReviewDetail review) {
        // sometimes the response has wrong image path as below:
        // avatar_path: /https://secure.gravatar.com/avatar/3593437cbd05cebe0a4ee753965a8ad1.jpg
        String avatarPath = review.getAuthorDetail().getAvatarPath();
        String imagePath = APIManager.IMAGE_BASE_URL + "/w154" + avatarPath;
        if (avatarPath != null && avatarPath.startsWith("/https://")) {
            imagePath = avatarPath.substring(1);
        }
        return imagePath;
    }

    public String getMoviePoint(MovieReview.ReviewDetail review) {
        return String.format(Locale.US, "%.1f", (float)review.getAuthorDetail().getRating() / 2);
    }

    public String getTimeAgo(MovieReview.ReviewDetail review) {
        String timeAgo = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date dateCreated = sdf.parse(review.getCreatedAt());
            long diff = Calendar.getInstance().getTime().getTime() - dateCreated.getTime();
            long daysAgo = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (daysAgo < 1) {
                long hoursAgo = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
                if (hoursAgo < 1) {
                    long minuteAgo = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
                    if (minuteAgo <= 1) {
                        timeAgo = minuteAgo + "minute ago";
                    } else {
                        timeAgo = minuteAgo + "minutes ago";
                    }
                } else if (hoursAgo == 1) {
                    timeAgo = hoursAgo + " hour ago";
                } else {
                    timeAgo = hoursAgo + " hours ago";
                }
            } else if (daysAgo == 1) {
                timeAgo = "yesterday";
            } else {
                timeAgo = daysAgo + " days ago";
            }
        } catch (Exception e) {
            Log.d(TAG, "error format dateCreated: " + e.getMessage());
        }
        return timeAgo;
    }
}
