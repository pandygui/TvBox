package com.wangw.tvbox.module.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.wangw.tvbox.module.TVItemDecoration;

/**
 * Created by wangw on 2018/3/1.
 */

public class TvGridView extends RecyclerView {

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
        mLayoutManager = new GridLayoutManager(getContext(), getSpanCount());
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
    }

    public int getSpanCount(){
        return 5;
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
