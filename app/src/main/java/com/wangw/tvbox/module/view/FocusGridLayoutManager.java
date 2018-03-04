package com.wangw.tvbox.module.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangw on 2018/3/4.
 */

public class FocusGridLayoutManager extends GridLayoutManager {

    private RecyclerView mRecyclerView;

    public FocusGridLayoutManager(Context context, RecyclerView recyclerView, int spanCount) {
        super(context, spanCount);
        this.mRecyclerView = recyclerView;
    }

    public FocusGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FocusGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FocusGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        if (direction == View.FOCUS_DOWN && mRecyclerView != null){
            View result = FocusFinder.getInstance().findNextFocus(mRecyclerView, focused, direction);
            if (result != null){
                return result;
            }else if (mRecyclerView.getDescendantFocusability() == ViewGroup.FOCUS_BLOCK_DESCENDANTS) {
                return mRecyclerView.getParent().focusSearch(focused, direction);
            } else {
                return focused;
            }
        }
        return super.onInterceptFocusSearch(focused, direction);
    }
}
