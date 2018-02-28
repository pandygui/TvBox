package com.wangw.tvbox.mangguo.model;

import com.wangw.tvbox.model.IVideoInfo;

/**
 * 单个视频详情
 * Created by wangw on 2018/2/28.
 */

public class MGVideoInfo implements IVideoInfo {

    /**
     * clipId : 6880
     * fstlvlId : 3
     * ic : 1
     * img : http://0img.hitv.com/preview/internettv/sp_images/ott/2016/dianying/6880/20160701112030053-new.jpg
     * partId : 0
     * playPartId : 1709779
     * rightCorner : {"color":"#E4B659","text":"VIP免费"}
     * se_updateTime : 2018-02-28 17:38:03
     * subtitle : 塞斯·罗根,周杰伦,卡梅隆·迪亚茨,克里斯托弗·瓦尔兹,汤姆·威尔金森
     * title : 青蜂侠
     * updateInfo : 7.0
     * views : 682
     */

    public String clipId;
    public String fstlvlId;
    public String ic;
    public String img;
    public String partId;
    public String playPartId;
    public RightCornerBean rightCorner;
    public String se_updateTime;
    public String subtitle;
    public String title;
    public String updateInfo;
    public String views;

    @Override
    public String getVideoPageUrl() {
        return "https://m.mgtv.com/b/"+clipId+"/"+playPartId+".html";
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static class RightCornerBean {
        /**
         * color : #E4B659
         * text : VIP免费
         */

        public String color;
        public String text;
    }


}
