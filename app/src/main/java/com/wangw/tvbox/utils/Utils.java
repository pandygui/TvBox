package com.wangw.tvbox.utils;

import android.content.Context;
import android.view.WindowManager;

import java.util.Calendar;

/**
 * Created by wangw on 2017/12/9.
 */

public class Utils {

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeigth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    public static String getMonthDay(){
        Calendar calendar = Calendar.getInstance();
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (month.length() < 2){
            month = "0"+month;
        }
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() < 2){
            day = "0" + day;
        }
        return "18"+month+ day;
    }

}
