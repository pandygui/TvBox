package com.wangw.tvbox.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.wangw.tvbox.R;

/**
 * Created by wangw on 2018/3/1.
 */

public class ScaleFocusListener implements View.OnFocusChangeListener {

    /**
     * No zoom factor.
     */
    public static final int ZOOM_FACTOR_NONE = 0;

    /**
     * A small zoom factor, recommended for large item views.
     */
    public static final int ZOOM_FACTOR_SMALL = 1;

    /**
     * A medium zoom factor, recommended for medium sized item views.
     */
    public static final int ZOOM_FACTOR_MEDIUM = 2;

    /**
     * A large zoom factor, recommended for small item views.
     */
    public static final int ZOOM_FACTOR_LARGE = 3;

    /**
     * An extra small zoom factor.
     */
    public static final int ZOOM_FACTOR_XSMALL = 4;

    private static final int DURATION_MS = 150;
    private int mScaleIndex;
    private final boolean mUseDimmer;

    public ScaleFocusListener(int scaleIndex, boolean useDimmer) {
        mScaleIndex = scaleIndex;
        mUseDimmer = useDimmer;
    }


    public void onInitializeView(View v){
        getOrCreateAnimator(v).animateFocus(false,true);;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        getOrCreateAnimator(v).animateFocus(hasFocus,false);
        if (hasFocus){
            v.setBackgroundColor(v.getResources().getColor(R.color.channel_selected));
        }else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private FocusAnimator getOrCreateAnimator(View view) {
        FocusAnimator animator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
        if (animator == null) {
            animator = new FocusAnimator(
                    view, getScale(view.getResources()), mUseDimmer, DURATION_MS);
            view.setTag(R.id.lb_focus_animator, animator);
        }
        return animator;
    }

    private float getScale(Resources res) {
        return mScaleIndex == ZOOM_FACTOR_NONE ? 1f :
                res.getFraction(getResId(mScaleIndex), 1, 1);
    }

    static int getResId(int zoomIndex) {
        switch (zoomIndex) {
            case ZOOM_FACTOR_SMALL:
                return android.support.v17.leanback.R.fraction.lb_focus_zoom_factor_small;
            case ZOOM_FACTOR_XSMALL:
                return android.support.v17.leanback.R.fraction.lb_focus_zoom_factor_xsmall;
            case ZOOM_FACTOR_MEDIUM:
                return android.support.v17.leanback.R.fraction.lb_focus_zoom_factor_medium;
            case ZOOM_FACTOR_LARGE:
                return android.support.v17.leanback.R.fraction.lb_focus_zoom_factor_large;
            default:
                return 0;
        }
    }

}
