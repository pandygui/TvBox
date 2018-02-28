package com.wangw.tvbox.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wangw on 2018/2/28.
 */

public class ImageLoader {


    public static void loadImg(@NonNull Context context, @NonNull String url, @NonNull ImageView iv, @DrawableRes int def, @DrawableRes int error, boolean fade) {
        if (url.isEmpty()) {
            return;
        }
        try {
            if (fade) {
                Glide.with(context).load(url).placeholder(def).error(error).into(iv);
            } else {
                Glide.with(context).load(url).placeholder(def).error(error).dontAnimate().into(iv);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    public static void loadImg(String url, ImageView iv) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            Glide.with(iv.getContext()).load(url).dontAnimate().into(iv);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
