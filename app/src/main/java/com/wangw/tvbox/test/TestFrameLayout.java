package com.wangw.tvbox.test;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by wangw on 2018/2/27.
 */

public class TestFrameLayout extends FrameLayout {

    private static final String TAG = "TestFrameLayout";

    private OnFocusSearchListener mListener;

    public TestFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        Log.d(TAG, "onRequestFocusInDescendants: direction="+direction+" , previouslyFocusedRect="+previouslyFocusedRect);
        if (mListener != null){
            if (mListener.onRequestFocusInDescendants(direction,previouslyFocusedRect))
                return true;
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        Log.d(TAG, "requestChildFocus: child="+child+" , focused="+focused);
        if (mListener != null){
            mListener.requestChildFocus(child,focused);
        }
        super.requestChildFocus(child, focused);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        Log.d(TAG, "focusSearch: focused="+focused+"， direction="+direction);
        if (mListener != null){
            View view = mListener.focusSearch(focused, direction);
            if (view != null)
                return view;
        }
        return super.focusSearch(focused, direction);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean consumed = super.dispatchKeyEvent(event);
        if (mListener != null){
            if (!consumed){
              consumed = mListener.onKey(getRootView(),event.getKeyCode(),event);
            }
        }
        return consumed;
    }

    public OnFocusSearchListener getListener() {
        return mListener;
    }

    public void setListener(OnFocusSearchListener listener) {
        mListener = listener;
    }

    public interface OnFocusSearchListener{
        View focusSearch(View focused, int direction);
        void requestChildFocus(View child, View focused);
        boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect);

        boolean onKey(View rootView, int keyCode, KeyEvent event);
    }
}
