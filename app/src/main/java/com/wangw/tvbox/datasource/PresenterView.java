package com.wangw.tvbox.datasource;

/**
 * Created by wangw on 2018/2/28.
 */

public interface PresenterView<P> extends SafeCallback {

    void setPresenter(P presenter);

    void showToast(String msg);

    void closeLoading();

    void showLoading();

    void showLoading(boolean cancelable);

    void showLoading(boolean cancelable,String msg);

    void showLoading(String msg);

}
