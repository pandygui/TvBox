package com.wangw.tvbox.utils;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangw.tvbox.R;
import com.wangw.tvbox.TvApp;

/**
 * Created by garfield on 1/17/17.
 */

public class ToastUtils {

    public static void showToast(@StringRes int stringResID) {
        showToast(TvApp.getAppContext().getResources().getString(stringResID));
    }

    /**
     * 在宿主Activity或Fragment可能被杀死的情景，优先使用此方法，比如分享后的回调
     *
     * @param hint
     */
    public static void showToast(final String hint) {
        TvApp.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = new Toast(TvApp.getAppContext());
                View view = LayoutInflater.from(TvApp.getAppContext()).inflate(R.layout.view_custom_toast, null);
                TextView content = (TextView) view.findViewById(R.id.tv_content);
                FrameLayout.LayoutParams pm = (FrameLayout.LayoutParams) content.getLayoutParams();
                pm.width = Utils.getScreenWidth(TvApp.getAppContext());
                view.setLayoutParams(pm);
                content.setText(hint);
                toast.setView(view);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
