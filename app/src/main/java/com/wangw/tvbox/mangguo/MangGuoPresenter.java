package com.wangw.tvbox.mangguo;

import com.wangw.tvbox.datasource.AbstractPresenter;
import com.wangw.tvbox.datasource.BaseDataSourceCallback;
import com.wangw.tvbox.datasource.PresenterView;
import com.wangw.tvbox.mangguo.database.MangguoDataSource;
import com.wangw.tvbox.mangguo.model.BaseMGModel;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.mangguo.model.MGVideoInfo;
import com.wangw.tvbox.mangguo.model.MGVideoList;
import com.wangw.tvbox.model.IVideoInfo;
import com.wangw.tvbox.net.TvResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoPresenter extends AbstractPresenter<MangGuoPresenter.MangGuoView> {


    private ChannelInfo mChannelInfo;
    private int mPageIndex = 1;
    private Map<String,String> mParams = new HashMap<>();
    private List<IVideoInfo> mVideos = new ArrayList<IVideoInfo>();
    private int mTotalSize;

    public MangGuoPresenter(MangGuoView view) {
        super(view);
    }

    @Override
    public void onStart() {
        showLoading();
        onLoadChannel();
    }

    private void onLoadChannel() {
        getDataSource().getChannelListConfig(new BaseDataSourceCallback<BaseMGModel<List<ChannelInfo>>>(this) {
            @Override
            public void onSuccess(TvResponse response, BaseMGModel<List<ChannelInfo>> o) {
                List<ChannelInfo> data = o.data;
                getView().initChannelView(data);
                setChannelInfo(data.get(0));
            }

        });
    }

    public void setChannelInfo(ChannelInfo channelInfo){
        if (channelInfo == mChannelInfo)
            return;
        mChannelInfo = channelInfo;
        mPageIndex = 1;
        mParams.clear();
        getVideoList();
    }

    public void updateVideoList(ChannelInfo.ItemsBeanXX.ItemsBeanX key, ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean value){
        mParams.put(key.eName,value.tagId);
        mPageIndex = 1;
    }

    public void loadMore(){
        mPageIndex ++;
        getVideoList();
    }

    private void getVideoList() {
//        showLoading();
        getDataSource().getVideoList(mChannelInfo.getFstlvlId(), mPageIndex, mParams, new BaseDataSourceCallback<BaseMGModel<MGVideoList>>(this) {
            @Override
            public void onSuccess(TvResponse response, BaseMGModel<MGVideoList> o) {
                if(mPageIndex == 1){
                    mVideos.clear();
                }
                int start = mVideos.size();
                List<MGVideoInfo> hitDocs = o.data.hitDocs;
                mVideos.addAll(hitDocs);
                mTotalSize = o.data.totalHits;
                getView().updateVideoList(mVideos,start,hitDocs.size());
            }

            @Override
            public void onFinish(TvResponse response) {
                super.onFinish(response);
                closeLoading();
            }
        });
    }

    public boolean hasMore(){
        return mTotalSize <= mVideos.size();
    }



    private MangguoDataSource getDataSource(){
        return MangguoDataSource.instance;
    }

    public interface MangGuoView extends PresenterView<MangGuoPresenter>{

        void initChannelView(List<ChannelInfo> data);

        void updateVideoList(List<IVideoInfo> videos, int start, int end);
    }
}
