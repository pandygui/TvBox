package com.wangw.tvbox.module;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangw.tvbox.utils.ScaleFocusListener;

/**
 * Created by wangw on 2018/3/1.
 */

public abstract class TvViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ScaleFocusListener mFocusListener;

    public TvViewHolder(View itemView) {
        super(itemView);
        mFocusListener = new ScaleFocusListener(ScaleFocusListener.ZOOM_FACTOR_SMALL, false);
        mFocusListener.onInitializeView(itemView);
        itemView.setOnFocusChangeListener(mFocusListener);
        itemView.setFocusable(true);
        itemView.setOnClickListener(this);
    }

    public void bindData(T data, int position) {

    }

}
