package com.wangw.tvbox.mangguo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.BaseActivity;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.mangguo.model.MGVideoInfo;

import java.util.List;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoActivity extends BaseActivity<MangGuoPresenter> implements MangGuoPresenter.MangGuoView {

    private MangGuoChannelView mChannelView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
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
        mLayoutManager = new GridLayoutManager(this,5);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MangGuoAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initChannelView(List<ChannelInfo> data) {
        mChannelView.setData(data);
    }

    @Override
    public void updateVideoList(List<MGVideoInfo> videos) {
        mAdapter.setData(videos);
    }
}
