package com.wangw.tvbox.net;

/**
 * Created by wangw on 2018/2/28.
 */

public enum Environment {

    MANGGUO_TV("芒果Tv");

    private String mName;

    Environment(String name){

    }


    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return mName;
    }
}
