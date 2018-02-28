package com.wangw.tvbox.mangguo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.ParseWebActivity;
import com.wangw.tvbox.mangguo.model.MGVideoInfo;
import com.wangw.tvbox.module.BaseAdapter;
import com.wangw.tvbox.utils.ImageLoader;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoAdapter extends BaseAdapter<MangGuoAdapter.MangGuoVideoViewHolder,MGVideoInfo> {


    @Override
    public MangGuoVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MangGuoVideoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mg_video,null));
    }

    @Override
    public void onBindViewHolder(MangGuoVideoViewHolder holder, int position) {
        holder.bindData(getData(position),position);
    }

    class MangGuoVideoViewHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener, View.OnClickListener {

        private MGVideoInfo mData;
        private int mPosition;

        private TextView mTvTitle;
        private ImageView mIvThumb;

        public MangGuoVideoViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvThumb = itemView.findViewById(R.id.iv_thumb);
            itemView.setFocusable(true);
            itemView.setOnFocusChangeListener(this);
            itemView.setOnClickListener(this);
        }

        public void bindData(MGVideoInfo data, int position) {
            mData = data;
            mPosition = position;
            ImageLoader.loadImg(data.img,mIvThumb);
            mTvTitle.setText(mData.title);

        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                v.setScaleX(1.5f);
                v.setScaleY(1.5f);
            }else {
                v.setScaleY(1);
                v.setScaleX(1);
            }
        }

        @Override
        public void onClick(View v) {
            ParseWebActivity.jumpTo(v.getContext(),mData.getVideoPageUrl());
        }
    }
}
