package com.wangw.tvbox.mangguo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.BaseActivity;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.model.IVideoInfo;
import com.wangw.tvbox.module.HeaderRecyclerViewAdapter;
import com.wangw.tvbox.module.view.TvGridView;

import java.util.List;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoActivity extends BaseActivity<MangGuoPresenter> implements MangGuoPresenter.MangGuoView {

    private MangGuoChannelView mChannelView;
    private TvGridView mRecyclerView;
    private MangGuoAdapter mAdapter;
    private MangGuoFilterView mFilterView;
    private HeaderRecyclerViewAdapter mHeaderViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangguo);
        mChannelView = findViewById(R.id.channelview);
        mChannelView.setChannelListener(new MangGuoChannelView.ChannelListener() {
            @Override
            public void setChannel(ChannelInfo info) {
                mPresenter.setChannelInfo(info);
//                mFilterView.setVisibility(View.GONE);
            }

            @Override
            public void onClickFilterView() {
//                mFilterView.setVisibility(View.VISIBLE);
            }
        });
        mRecyclerView = findViewById(R.id.videos);
        new MangGuoPresenter(this);
        initRecyclerView();
        mPresenter.onStart();
    }

    private void initRecyclerView() {
        mAdapter = new MangGuoAdapter();
        mHeaderViewAdapter = new HeaderRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(mHeaderViewAdapter);
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
        if(mRecyclerView.getLayoutManager() instanceof GridLayoutManager){
            ((GridLayoutManager)mRecyclerView.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (HeaderRecyclerViewAdapter.isHeaderView(mHeaderViewAdapter.getItemViewType(position)))
                        return mRecyclerView.getSpanCount();
                    else
                        return 1;
                }
            });
        }
        mFilterView = new MangGuoFilterView(this);
        mFilterView.setListener(new MangGuoFilterView.MGFilterListener(){
            @Override
            public void setFilter(ChannelInfo.ItemsBeanXX.ItemsBeanX item, ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean bean) {
                mPresenter.updateVideoList(item,bean);
            }
        });
        mHeaderViewAdapter.addHeader(mFilterView);
    }

    @Override
    public void initChannelView(List<ChannelInfo> data) {
        mChannelView.setData(data);
        mChannelView.requestFocusInit();
    }

    @Override
    public void updateVideoList(List<IVideoInfo> videos, int start, int end) {
        if (start == 0){
            mAdapter.setData(videos);
        }else {
            mAdapter.setData(videos, start, end);
        }
        mFilterView.requestFocus();
    }

    @Override
    public void updateFilterView(ChannelInfo.ItemsBeanXX itemsBeanXX) {
        mFilterView.setData(itemsBeanXX.items);
    }
}

