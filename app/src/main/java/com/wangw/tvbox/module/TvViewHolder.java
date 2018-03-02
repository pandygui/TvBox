package com.wangw.tvbox.module;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangw.tvbox.R;

/**
 * Created by wangw on 2018/3/1.
 */

public abstract class TvViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {
    private ScaleFocusListener mFocusListener;

    public TvViewHolder(View itemView) {
        super(itemView);
        mFocusListener = new ScaleFocusListener(ScaleFocusListener.ZOOM_FACTOR_MEDIUM, false,this);
        mFocusListener.onInitializeView(itemView);
        itemView.setOnFocusChangeListener(mFocusListener);
        itemView.setFocusable(true);
        itemView.setOnClickListener(this);
    }

    public void bindData(T data, int position) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            v.setBackgroundColor(v.getResources().getColor(R.color.channel_selected));
            v.bringToFront();
        }else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
