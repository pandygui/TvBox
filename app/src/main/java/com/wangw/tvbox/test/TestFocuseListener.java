package com.wangw.tvbox.test;

import android.content.res.Resources;
import android.view.View;

import com.wangw.tvbox.R;

import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_LARGE;
import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_MEDIUM;
import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_NONE;
import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_SMALL;
import static android.support.v17.leanback.widget.FocusHighlight.ZOOM_FACTOR_XSMALL;

/**
 * Created by wangw on 2018/2/27.
 */

public class TestFocuseListener implements View.OnFocusChangeListener {

    private int mScaleIndex = ZOOM_FACTOR_LARGE;
    private boolean mUseDimmer = true;
    private int DURATION_MS = 150;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
//        if (hasFocus){
//            v.setBackgroundColor(Color.YELLOW);
//        }else {
//            v.setBackgroundColor(Color.WHITE);
//        }
        v.setPivotX(v.getWidth()/2);
        v.setPivotY(v.getHeight()/2);
        getOrCreateAnimator(v).animateFocus(hasFocus,false);

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
                return R.fraction.lb_focus_zoom_factor_small;
            case ZOOM_FACTOR_XSMALL:
                return R.fraction.lb_focus_zoom_factor_xsmall;
            case ZOOM_FACTOR_MEDIUM:
                return R.fraction.lb_focus_zoom_factor_medium;
            case ZOOM_FACTOR_LARGE:
                return R.fraction.lb_focus_zoom_factor_large;
            default:
                return 0;
        }
    }
}
