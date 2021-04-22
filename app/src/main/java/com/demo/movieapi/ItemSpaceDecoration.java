package com.demo.movieapi;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemSpaceDecoration extends RecyclerView.ItemDecoration {
    /**
     * Space in pixel
     */
    private int spacePx = 0;
    private boolean mVerticalOrientation = false;

    /**
     * Constructor
     * @param spaceDp space in dp
     */
    public ItemSpaceDecoration(int spaceDp) {
        this.spacePx = Utils.dpToPx(spaceDp);
    }

    public ItemSpaceDecoration(int spaceDp, boolean verticalOrientation) {
        this.spacePx = Utils.dpToPx(spaceDp);
        this.mVerticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mVerticalOrientation) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.set(0, spacePx, 0, spacePx);
            } else {
                outRect.set(0, 0, 0, spacePx);
            }
        } else {
            if (parent.getAdapter() != null &&
                    parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.right = spacePx;
            }
        }
    }
}
