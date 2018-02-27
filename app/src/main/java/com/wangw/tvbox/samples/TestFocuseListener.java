package com.wangw.tvbox.samples;

import android.graphics.Color;
import android.view.View;

/**
 * Created by wangw on 2018/2/27.
 */

public class TestFocuseListener implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            v.setBackgroundColor(Color.YELLOW);
        }else {
            v.setBackgroundColor(Color.WHITE);
        }
    }
}
