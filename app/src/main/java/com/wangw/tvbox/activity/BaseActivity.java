package com.wangw.tvbox.activity;

import android.support.v4.app.FragmentActivity;

import com.wangw.tvbox.module.LoadingDialog;
import com.wangw.tvbox.utils.ToastUtils;

/**
 * Created by wangw on 2018/2/24.
 */

public class BaseActivity extends FragmentActivity {

    protected LoadingDialog mLoading;

    public void showLoading() {
        showLoading(true);
    }

    public void showLoading(boolean cancelable) {
        showLoading(cancelable,"");
    }

    public void showLoading(boolean cancelable,String title) {
        if (isCancel())
            return;
        if (mLoading == null) {
            mLoading = new LoadingDialog(this);
        }
        mLoading.setHintText(title);
        mLoading.setCancelable(cancelable);
        if (!mLoading.isShowing()) {
            mLoading.show();
            mLoading.setShowNewLoading();
        }
    }

    public void showToast(String text) {
        ToastUtils.showToast(text);
    }

    @Override
    protected void onDestroy() {
        closeLoading();
        super.onDestroy();
    }

    public void closeLoading() {
        if (mLoading != null)
            mLoading.dismiss();
    }

    public boolean isCancel() {
        return isDestroyed() || isFinishing();
    }
}
