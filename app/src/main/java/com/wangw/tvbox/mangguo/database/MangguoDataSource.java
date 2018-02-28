package com.wangw.tvbox.mangguo.database;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.TypeReference;
import com.wangw.tvbox.datasource.BaseDataSource;
import com.wangw.tvbox.datasource.BaseDataSourceCallback;
import com.wangw.tvbox.mangguo.model.BaseMGModel;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.mangguo.model.MGTeleplays;
import com.wangw.tvbox.mangguo.model.MGVideoList;
import com.wangw.tvbox.net.AbstractRequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangguoDataSource extends BaseDataSource{

    public static MangguoDataSource instance = new MangguoDataSource();

    @NonNull
    private String getServerUrl() {
        return "https://pianku.api.mgtv.com/rider/list/msite";
    }

    /**
     * 获取频道列表
     */
    public void getChannelListConfig(BaseDataSourceCallback<BaseMGModel<List<ChannelInfo>>> callback){
        getJson("https://pianku.api.mgtv.com/rider/channel-list-config?platform=msite&abroad=0&_support=10000000",
                callback,
                new TypeReference<BaseMGModel<List<ChannelInfo>>>(){});
    }

    /**
     * 获取视频列表
     * @param fstlvlId  {@link ChannelInfo.ItemsBeanXX#fstlvlId}
     * @param params key : {@link ChannelInfo.ItemsBeanXX.ItemsBeanX#eName}
     *               value : {@link ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean#tagId}
     * @param pageIndex 从1开始
     * @param callback
     */
    public void getVideoList(String fstlvlId,int pageIndex,Map<String,String> params, BaseDataSourceCallback<BaseMGModel<MGVideoList>> callback){
        AbstractRequestParams rp = getRequestParams(getServerUrl()+"?abroad=0&_support=10000000&isfull=.html&pc=20&pn="+pageIndex+"&fstlvlId="+fstlvlId);
        if (params != null){
            for (String key : params.keySet()) {
                rp.addQueryStringParameter(key,params.get(key));
            }
        }
        getJson(rp,callback,new TypeReference<BaseMGModel<MGVideoList>>(){});
    }

    /**
     * 根据Id获取电视连续剧集合
     * @param partId    See {@link com.wangw.tvbox.mangguo.model.MGVideoInfo#partId}
     * @param pageIndex
     * @param callback
     */
    public void getTeleplays(String partId, int pageIndex, BaseDataSourceCallback<BaseMGModel<MGTeleplays>> callback){
        getJson(getServerUrl()+"?abroad=0&needLocate=0&_support=10000000&partId="+partId+"&pageSize="+200+"&pageNum="+pageIndex,
                callback,
                new TypeReference<BaseMGModel<MGTeleplays>>(){});
    }


    @Override
    protected AbstractRequestParams getRequestParams(String url) {
        return new MangGuoRequestParams(url);
    }
}
