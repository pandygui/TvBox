package com.wangw.tvbox.mangguo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.BaseActivity;
import com.wangw.tvbox.datasource.BaseDataSourceCallback;
import com.wangw.tvbox.mangguo.database.MangguoDataSource;
import com.wangw.tvbox.mangguo.model.MGSearchInfo;
import com.wangw.tvbox.mangguo.model.MGSearchList;
import com.wangw.tvbox.model.IVideoInfo;
import com.wangw.tvbox.module.view.TvGridView;
import com.wangw.tvbox.net.TvResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangw on 2018/3/1.
 */

public class MangGuoSearchActivity extends BaseActivity {

    private EditText mEvSearch;
    private TvGridView mItems;
    private MangGuoAdapter mAdapter;
    private int mPageIndex;
    private boolean mHasMore;
    private List<IVideoInfo> mDatas = new ArrayList<>();
    private boolean mGetIng;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mg_search);
        initView();
    }

    private void initView() {
        mEvSearch = findViewById(R.id.ev_search);
        mItems = findViewById(R.id.items);
        mItems.setListener(new TvGridView.TvGridViewListener() {
            @Override
            public void onLoadMore() {
                mPageIndex++;
                searchVideo(mEvSearch.getText().toString());
            }

            @Override
            public boolean hasMore() {
                return mHasMore && !mGetIng;
            }
        });
        mAdapter = new MangGuoAdapter();
        mItems.setAdapter(mAdapter);

        mEvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEvSearch.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onSearch(mEvSearch.getText().toString());
                    }
                },500);

            }
        });
        mEvSearch.requestFocus();
    }

    private void onSearch(String key) {
        if (TextUtils.isEmpty(key)){
            mAdapter.clearData();
            mEvSearch.requestFocus();
            return;
        }
        mPageIndex = 1;
        searchVideo(key);
    }

    private void searchVideo(String key) {
        if (mGetIng)
            return;
        this.mGetIng = true;
        MangguoDataSource.instance.searchVideo(key, mPageIndex, new BaseDataSourceCallback<MGSearchList>(this) {
            @Override
            public void onSuccess(TvResponse response, MGSearchList o) {
                if (mPageIndex == 1){
                    mAdapter.clearData();
                    mDatas.clear();
                }
                if (o.data == null){
                    showToast("没有找到要搜索的视频");
                    return;
                }
                mHasMore = o.data.hasMore;
                if (o.data.contents == null || o.data.contents.isEmpty()){
                    showToast("没有找到 \'"+o.data.query+"\' 相关的视频内容");
                    return;
                }
                Collection<? extends MGSearchInfo> cl = getContentList(o.data.contents);
                int start = mDatas.size();
                mDatas.addAll(cl);
                if (mPageIndex == 1) {
                    mAdapter.setData(mDatas);
                }else {
                    mAdapter.setData(mDatas,start,cl.size());
                }
            }

            @Override
            public void onError(TvResponse response) {
                mGetIng = true;
                super.onError(response);
            }

            @Override
            public void onFinish(TvResponse response) {
                mGetIng = false;
                super.onFinish(response);
            }
        });
    }

    private Collection<? extends MGSearchInfo> getContentList(List<MGSearchList.DataBean.ContentsBean> contents) {
        ArrayList<MGSearchInfo> list = new ArrayList<>();
        for (MGSearchList.DataBean.ContentsBean content : contents) {
            if (TextUtils.equals(content.type,"cline") || TextUtils.equals(content.type,"qcorr")){
                continue;
            }
            for (MGSearchInfo info : content.data) {
                list.add(info);
            }
        }
        return list;
    }


}
