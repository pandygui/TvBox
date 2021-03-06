package com.wangw.tvbox;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import org.xutils.x;

/**
 * Created by wangw on 2018/2/24.
 */

public class TvApp extends Application {


    public static TvApp instance;
    protected static Handler uiHandler = new Handler(Looper.getMainLooper());
    protected static HandlerThread mBackThread = new HandlerThread("back");
    protected static Handler mBackHandler;

    public static Application getAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNetwork();
    }

    public static void runOnUiThread(Runnable r) {
        uiHandler.post(r);
    }

    public static void postBack(Runnable r){
        if (mBackHandler == null){
            mBackThread.start();
            mBackHandler = new Handler(mBackThread.getLooper());
        }
        mBackHandler.post(r);
    }

    public void initNetwork(){
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
