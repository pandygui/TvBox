package com.wangw.tvbox.module;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wangw on 2018/3/2.
 */

public class FocusSearchLinearLayout extends LinearLayout {

    private FocusSearchListener mListener;

    public FocusSearchLinearLayout(Context context) {
        super(context);
    }

    public FocusSearchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusSearchLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View view = null;
        if (mListener != null){
            view = mListener.focusSearch(focused,direction);
        }
        if (view != null)
            return view;
        else
            return super.focusSearch(focused, direction);
    }

    public FocusSearchListener getListener() {
        return mListener;
    }

    public void setListener(FocusSearchListener listener) {
        mListener = listener;
    }
}
