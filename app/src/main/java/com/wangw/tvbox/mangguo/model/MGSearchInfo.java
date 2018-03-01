package com.wangw.tvbox.mangguo.model;

import android.text.Html;
import android.text.TextUtils;

import com.wangw.tvbox.model.IVideoInfo;

import java.util.List;

/**
 * Created by wangw on 2018/3/1.
 */

public class MGSearchInfo implements IVideoInfo{


    /**
     * dataType : 0
     * desc : ["3440次播放"]
     * icon : 1
     * img : https://3img.imgo.tv/preview/jinying/prev/04duanpiantupianchucun/mt111301.jpg_220x123.jpg
     * jumpKind : 3
     * rightBottomCorner : 02:33
     * rpt : _client=msite&v5=1&act=click&cache=0&topvideo=0&recommend=0&query=%E9%97%A8%E5%BE%92&id=9d1df6f2d1145308709aa840b1156ca2&idx=5&tp=1&liveshow=0&list=0&zone=0&source=imgo
     * source :
     * title : <B>门徒</B>预告 吴彦祖搏命勇斗大毒枭
     * url : /b/289/342322.html
     * videoCount : 0
     */

    public String dataType;
    public String icon;
    public String img;
    public String jumpKind;
    public String rightBottomCorner;
    public String rpt;
    public String source;
    public String title;
    public String url;
    public int videoCount;
    public List<String> desc;


    @Override
    public String getVideoPageUrl() {
        if (TextUtils.isEmpty(url))
            return "";
        else if (url.startsWith("http"))
            return url;
        else
        return "https://m.mgtv.com"+url;
    }

    @Override
    public String getTitle() {
        return Html.fromHtml(title).toString();
    }

    @Override
    public String getThumb() {
        return img;
    }

    public String getDesc(){
        if (desc == null || desc.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append("来源:")
                .append(source)
                .append(", ");
        for (String s : desc) {
            sb.append(s)
                    .append(",");
        }
        return sb.toString();
    }
}
