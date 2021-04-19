package com.demo.movieapi.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.R;
import com.demo.movieapi.Utils;
import com.demo.movieapi.activity.MainActivity;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.APIManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrendingRecyclerViewAdapter extends RecyclerView.Adapter<TrendingRecyclerViewAdapter.TrendingViewHolder> {

    private static final String TAG = TrendingRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<TMDBResponse.Movie> trendingList;

    public TrendingRecyclerViewAdapter(Context context, List<TMDBResponse.Movie> trendingList) {
        this.context = context;
        this.trendingList = trendingList;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie, parent, false);
        return new TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position);
        String imagePath = APIManager.IMAGE_BASE_URL + "/w300" + trendingList.get(position).getBackdropPath();
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(holder.imvTrending, new Callback() {
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
        return trendingList.size();
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imvTrending;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imvTrending = itemView.findViewById(R.id.imv_trending);
            int widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
            Log.d(TAG, "widthPixels: " + widthPixels);
            int widthDp = Utils.pxToDp(widthPixels);
            Log.d(TAG, "widthDp: " + widthDp);
            int spacePx = Utils.dpToPx(36);
            Log.d(TAG, "spacePx: " + spacePx);
            imvTrending.getLayoutParams().width = widthPixels - spacePx;
            imvTrending.requestLayout();
        }
    }
}
