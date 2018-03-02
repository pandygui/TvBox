package com.wangw.tvbox.mangguo;

import android.graphics.Color;
import android.support.v17.leanback.widget.ShadowOverlayContainer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.ParseWebActivity;
import com.wangw.tvbox.mangguo.model.MGSearchInfo;
import com.wangw.tvbox.mangguo.model.MGVideoInfo;
import com.wangw.tvbox.model.IVideoInfo;
import com.wangw.tvbox.module.TvAdapter;
import com.wangw.tvbox.module.TvViewHolder;
import com.wangw.tvbox.utils.ImageLoader;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoAdapter extends TvAdapter<MangGuoAdapter.MangGuoVideoViewHolder, IVideoInfo> {

    public MangGuoAdapter() {
        super();
    }

    @Override
    protected MangGuoVideoViewHolder createViewHoler(ShadowOverlayContainer wraper) {
        return new MangGuoVideoViewHolder(wraper);
    }

    @Override
    protected int getLayResult(int viewType) {
        return R.layout.item_mg_video;
    }

    class MangGuoVideoViewHolder extends TvViewHolder<IVideoInfo>  {

        private IVideoInfo mData;
        private int mPosition;

        private TextView mTvTitle;
        private ImageView mIvThumb;
        private TextView mTvSubTitle;
        private TextView mTvMask;


        public MangGuoVideoViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvThumb = itemView.findViewById(R.id.iv_thumb);
            mTvSubTitle = itemView.findViewById(R.id.tv_sub_title);
            mTvMask = itemView.findViewById(R.id.tv_mask);
        }

        @Override
        public void bindData(IVideoInfo data, int position) {
            mData = data;
            mPosition = position;
            ImageLoader.loadImg(mData.getThumb(), mIvThumb);
            mTvTitle.setText(mData.getTitle());
            mTvMask.setVisibility(View.INVISIBLE);
            if (mData instanceof MGVideoInfo) {
                MGVideoInfo info = ((MGVideoInfo) mData);
                mTvSubTitle.setText( info.subtitle);
                if (info.rightCorner != null){
                    mTvMask.setText(info.rightCorner.text);
                    if (TextUtils.isEmpty(info.rightCorner.color))
                        mTvMask.setBackgroundColor(mTvMask.getResources().getColor(R.color.mask_default_bg));
                    else
                        mTvMask.setBackgroundColor(Color.parseColor(info.rightCorner.color));
                    mTvMask.setVisibility(View.VISIBLE);
                }
            } else if (mData instanceof MGSearchInfo) {
                mTvSubTitle.setText(((MGSearchInfo) mData).getDesc());
            }

        }

        @Override
        public void onClick(View v) {
            ParseWebActivity.jumpTo(v.getContext(), mData.getVideoPageUrl());
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            super.onFocusChange(v, hasFocus);
            if (hasFocus){
                mTvSubTitle.setVisibility(View.VISIBLE);
            }else {
                mTvSubTitle.setVisibility(View.INVISIBLE);
            }
        }
    }
}
