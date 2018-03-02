package com.wangw.tvbox.module;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TVItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public TVItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int headerCount = 0;
        if (parent.getAdapter() instanceof HeaderRecyclerViewAdapter) {
            headerCount = ((HeaderRecyclerViewAdapter) parent.getAdapter()).getHeaderSize();
        }
        int position = parent.getChildAdapterPosition(view) - headerCount; // item position
        if (position < 0)
            return;
        int column = position % spanCount; // item column
        if (includeEdge) {
            outRect.left = position % spanCount == 0 ? (int) (spacing * 1.3) : spacing - column * spacing / spanCount; // spacing - column * ((1f / mSpanCount) * spacing)
            outRect.right =(column + 1) * spacing / spanCount; // (column + 1) * ((1f / mSpanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / mSpanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    mSpanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}