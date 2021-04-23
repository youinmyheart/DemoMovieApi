package com.demo.movieapi;

import androidx.recyclerview.widget.RecyclerView;

public interface OnClickViewHolderItemListener {
    void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
}
