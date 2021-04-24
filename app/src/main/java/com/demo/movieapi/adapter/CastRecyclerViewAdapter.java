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

import com.demo.movieapi.R;
import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.repository.APIManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {
    private static final String TAG = CastRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private List<CastCrew.Cast> castList;

    public CastRecyclerViewAdapter(Context context, List<CastCrew.Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_cast, parent, false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position);
        String imagePath = APIManager.IMAGE_BASE_URL + "/w154" + castList.get(position).getProfilePath();
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

        CastCrew.Cast cast = castList.get(position);
        holder.tvName.setText(cast.getName());
        holder.tvCharacter.setText(cast.getCharacter());
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        ImageView imvMovie;
        TextView tvName;
        TextView tvCharacter;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            imvMovie = itemView.findViewById(R.id.imv_movie);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCharacter = itemView.findViewById(R.id.tv_character);
        }
    }
}
