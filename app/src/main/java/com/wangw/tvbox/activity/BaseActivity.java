package com.wangw.tvbox.activity;

import android.support.v4.app.FragmentActivity;

import com.wangw.tvbox.datasource.Presenter;
import com.wangw.tvbox.datasource.PresenterView;
import com.wangw.tvbox.datasource.SafeCallback;
import com.wangw.tvbox.module.view.LoadingDialog;
import com.wangw.tvbox.utils.ToastUtils;

/**
 * Created by wangw on 2018/2/24.
 */

public class BaseActivity<P extends Presenter> extends FragmentActivity implements SafeCallback,PresenterView<P> {

    protected LoadingDialog mLoading;
    protected P mPresenter;

    public void showLoading() {
        showLoading(true);
    }

    public void showLoading(boolean cancelable) {
        showLoading(cancelable,"");
    }

    @Override
    public void showLoading(String msg) {
        showLoading(true,msg);
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

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
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

    @Override
    public boolean isCancel() {
        return isDestroyed() || isFinishing();
    }
}
