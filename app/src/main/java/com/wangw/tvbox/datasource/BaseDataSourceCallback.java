package com.wangw.tvbox.datasource;

import com.wangw.tvbox.net.TvResponse;
import com.wangw.tvbox.utils.ToastUtils;

/**
 * Created by wangw on 2018/2/28.
 */

public abstract class BaseDataSourceCallback<T> implements DataSourceCallback<T> {

    private SafeCallback mSafeCallback;

    @Deprecated
    public BaseDataSourceCallback() {
    }

    public BaseDataSourceCallback(SafeCallback safeCallback) {
        mSafeCallback = safeCallback;
    }

    @Override
    public void onError(TvResponse response) {
        ToastUtils.showToast(response.getError().getMessage());
    }

    @Override
    public void onFinish(TvResponse response) {
    }

    @Override
    public void onCancel(TvResponse response) {
    }

    @Override
    public boolean isCancel() {
        if (mSafeCallback != null)
            return mSafeCallback.isCancel();
        else
            return false;
    }
}
