package com.demo.movieapi.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.OnClickViewHolderItemListener;
import com.demo.movieapi.R;
import com.demo.movieapi.model.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenreRecyclerViewAdapter extends RecyclerView.Adapter<GenreRecyclerViewAdapter.GenreViewHolder> {
    private static final String TAG = GenreRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<GenreResponse.Genre> genreList;
    private OnClickViewHolderItemListener listener;

    public GenreRecyclerViewAdapter(Context context, List<GenreResponse.Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    public void setOnClickItemListener(OnClickViewHolderItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_movie, parent, false);
        return new GenreRecyclerViewAdapter.GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position);
        String genre = genreList.get(position).getName();
        holder.tvGenre.setText(genre);

        List<GradientDrawable> drawableList = getListGradientDrawable();
        holder.container.setBackground(drawableList.get(position % 3));
        holder.container.setOnClickListener(new View.OnClickListener() {
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
        return genreList.size();
    }

    private List<GradientDrawable> getListGradientDrawable() {
        List<GradientDrawable> ret = new ArrayList<>();
        GradientDrawable gd1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xFF00CBCF, 0xFF007AD9});
        GradientDrawable gd2 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xFF7591E8, 0xFF87CEF8});
        GradientDrawable gd3 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xFFFF80B5, 0xFFFF5A9F});
        ret.add(gd1);
        ret.add(gd2);
        ret.add(gd3);
        return ret;
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        RelativeLayout container;
        TextView tvGenre;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            container = itemView.findViewById(R.id.genre_container);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }
}
