package com.wangw.tvbox.module.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wangw.tvbox.module.TVItemDecoration;

/**
 * Created by wangw on 2018/3/1.
 */

public class TvGridView extends RecyclerView {

    private static final String TAG = "TvGridView";
    private GridLayoutManager mLayoutManager;
    private TvGridViewListener mListener;

    public TvGridView(Context context) {
        super(context);
        onInitView();
    }

    public TvGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onInitView();
    }

    public TvGridView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onInitView();
    }

    private void onInitView() {
        mLayoutManager = new FocusGridLayoutManager(getContext(),this, getSpanCount());
        setLayoutManager(mLayoutManager);
        addItemDecoration(new TVItemDecoration(getSpanCount(),30,true));
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (getListener() != null &&
                        newState == RecyclerView.SCROLL_STATE_IDLE
                        && getAdapter() != null && getAdapter().getItemCount() > 0
                        && !getListener().hasMore()) {
                    int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                    int totalItemCount = mLayoutManager.getItemCount();
                    if (totalItemCount - 1 == lastVisiblePosition) {
                        getListener().onLoadMore();
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }
                super.onScrollStateChanged(recyclerView,newState);
            }
        });
        setItemAnimator(null);
    }

    public int getSpanCount(){
        return 5;
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        Log.d(TAG, "requestChildFocus: child="+child+" , focused"+focused);
        super.requestChildFocus(child, focused);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        Log.d(TAG, "focusSearch: focused="+focused+" , direction="+direction);
        return super.focusSearch(focused, direction);
    }

    public TvGridViewListener getListener() {
        return mListener;
    }

    public void setListener(TvGridViewListener listener) {
        mListener = listener;
    }

    public interface TvGridViewListener{
        void onLoadMore();

        boolean hasMore();
    }
}
