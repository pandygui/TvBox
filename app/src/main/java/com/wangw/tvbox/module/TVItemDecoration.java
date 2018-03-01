package com.wangw.tvbox.module;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangw on 2018/3/1.
 */

public class TVItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacingHorizontal; //水平方向间隔
    private int spacingVertical;   //竖直方向间隔

    public TVItemDecoration(int spanCount, int spacingHorizontal, int spacingVertical) {
        this.spanCount = spanCount;
        this.spacingHorizontal = spacingHorizontal;
        this.spacingVertical = spacingVertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        outRect.left = column * spacingHorizontal / spanCount; // column * ((1f / mSpanCount) * spacing)
        outRect.right = spacingHorizontal - (column + 1) * spacingHorizontal / spanCount; // spacing - (column + 1) * ((1f /    mSpanCount) * spacing)
        outRect.top = 0; // item top
        outRect.bottom = 0; // item bottom
        if (position >= spanCount) {
            outRect.top = spacingVertical; // item top
        }
    }

}
