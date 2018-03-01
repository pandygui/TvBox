package com.wangw.tvbox.mangguo.model;

import com.wangw.tvbox.model.IVideoInfo;

/**
 * 单集电视连续剧详情
 * Created by wangw on 2018/2/28.
 */

public class MGTeleplayInfo implements IVideoInfo {

    /**
     * chargeType : 0
     * clipId : 322227
     * desc : 第1集
     * duration : 14:18
     * image : https://0img.hitv.com/preview/sp_images/2018/dianshiju/322227/4278496/20180208132244628.jpg
     * isIntact : 1
     * isnew : false
     * partId : 4278496
     * playCount : 15.1万
     * subtitle : 路非为爱回国 辛辰避而不见
     * title : 一路繁花相送 繁花纯享版 第1集
     * updateTime : 2018-02-11 00:00:08.0
     * videoIndex : 1
     */

    public int chargeType;
    public int clipId;
    public String desc;
    public String duration;
    public String image;
    public int isIntact;
    public boolean isnew;
    public int partId;
    public String playCount;
    public String subtitle;
    public String title;
    public String updateTime;
    public String videoIndex;


    @Override
    public String getVideoPageUrl() {
        return "https://m.mgtv.com/b/"+clipId+"/"+partId+".html";
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getThumb() {
        return image;
    }
}
