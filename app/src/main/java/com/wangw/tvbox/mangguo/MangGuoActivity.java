package com.wangw.tvbox.mangguo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.BaseActivity;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.model.IVideoInfo;
import com.wangw.tvbox.module.TvGridView;

import java.util.List;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoActivity extends BaseActivity<MangGuoPresenter> implements MangGuoPresenter.MangGuoView {

    private MangGuoChannelView mChannelView;
    private TvGridView mRecyclerView;
    private MangGuoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangguo);
        mChannelView = findViewById(R.id.channelview);
        mChannelView.setChannelListener(new MangGuoChannelView.ChannelListener() {
            @Override
            public void setChannel(ChannelInfo info) {
                mPresenter.setChannelInfo(info);
            }
        });
        mRecyclerView = findViewById(R.id.videos);
        new MangGuoPresenter(this);
        initRecyclerView();
        mPresenter.onStart();
    }

    private void initRecyclerView() {
        mAdapter = new MangGuoAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setListener(new TvGridView.TvGridViewListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }

            @Override
            public boolean hasMore() {
                return mPresenter.hasMore();
            }
        });
    }

    @Override
    public void initChannelView(List<ChannelInfo> data) {
        mChannelView.setData(data);
        mChannelView.requestFocus();
    }

    @Override
    public void updateVideoList(List<IVideoInfo> videos, int start, int end) {
        if (start == 0){
            mAdapter.setData(videos);
        }else {
            mAdapter.setData(videos, start, end);
        }
    }
}
