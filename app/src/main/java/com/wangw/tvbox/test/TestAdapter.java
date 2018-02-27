package com.wangw.tvbox.test;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangw.tvbox.samples.TestFocuseListener;
import com.wangw.tvbox.utils.ToastUtils;

import java.util.List;

/**
 * Created by wangw on 2018/2/27.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {


    private List<String> mList;
        private String mTitle;


    public void setList(List<String> list,String title) {
        mList = list;
        this.mTitle = title;
        notifyDataSetChanged();
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
            holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder{

        private TextView mTv;
        private String mData;

        public TestViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView;
            mTv.setTextColor(Color.BLACK);
            mTv.setPadding(20,20,20,20);
            mTv.setFocusable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(mData);
                }
            });
            itemView.setOnFocusChangeListener(new TestFocuseListener());

        }

        public void bindData(String s) {
            mData = mTitle+"_"+s;
            mTv.setText(mData);
        }
    }
}
