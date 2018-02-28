package com.wangw.tvbox.datasource;

/**
 * Created by wangw on 2018/2/28.
 */

public abstract class AbstractPresenter<V extends PresenterView> implements Presenter,SafeCallback {

    public String TAG = getClass().getSimpleName();
    protected V mView;

    public AbstractPresenter(V view) {
        this.mView = view;
        if (mView != null)
            this.mView.setPresenter(this);
    }

    public V getView() {
        return mView;
    }

    protected void showLoading(String msg){
        getView().showLoading(msg);
    }

    protected void showLoading(boolean cancelable){
        getView().showLoading(cancelable);
    }

    protected void showLoading(String msg,boolean cancelable){
        getView().showLoading(cancelable,msg);
    }


    protected void showLoading(){
        getView().showLoading();
    }

    protected void closeLoading() {
        getView().closeLoading();
    }


    protected void showToast(String msg){
        getView().showToast(msg);
    }

    @Override
    public boolean isCancel() {
        return getView().isCancel();
    }
}
