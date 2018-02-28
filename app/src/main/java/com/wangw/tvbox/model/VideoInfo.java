package com.wangw.tvbox.model;

/**
 * Created by wangw on 2018/2/28.
 */

public class VideoInfo {

    public String vdieoPageUrl;
    public String title;
    public String subTitle;
    public String time;

    public VideoInfo(IVideoInfo videoInfo) {
        this(videoInfo.getVideoPageUrl(),videoInfo.getTitle());
    }

    public VideoInfo(String vdieoPage, String title) {
        this.vdieoPageUrl = vdieoPage;
        this.title = title;
    }

    public VideoInfo() {
    }
}
