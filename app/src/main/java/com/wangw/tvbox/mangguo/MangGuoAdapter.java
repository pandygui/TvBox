package com.wangw.tvbox.mangguo;

import android.support.v17.leanback.widget.ShadowOverlayContainer;
import android.support.v17.leanback.widget.ShadowOverlayHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.TvApp;
import com.wangw.tvbox.activity.ParseWebActivity;
import com.wangw.tvbox.mangguo.model.MGVideoInfo;
import com.wangw.tvbox.module.BaseAdapter;
import com.wangw.tvbox.utils.ImageLoader;
import com.wangw.tvbox.utils.ScaleFocusListener;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoAdapter extends BaseAdapter<MangGuoAdapter.MangGuoVideoViewHolder,MGVideoInfo> {

    private ShadowOverlayHelper mShadowOverlayHelper;

    public MangGuoAdapter() {
        mShadowOverlayHelper = new ShadowOverlayHelper.Builder()
                .needsOverlay(true)
                .needsShadow(false)
                .needsRoundedCorner(true)
                .preferZOrder(true)
                .keepForegroundDrawable(true)
                .options(ShadowOverlayHelper.Options.DEFAULT)
                .build(TvApp.getAppContext());
    }

    @Override
    public MangGuoVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShadowOverlayContainer wraper = mShadowOverlayHelper.createShadowOverlayContainer(parent.getContext());
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mg_video, null);
        wraper.wrap(view);
        return new MangGuoVideoViewHolder(wraper);
    }

    @Override
    public void onBindViewHolder(MangGuoVideoViewHolder holder, int position) {
        holder.bindData(getData(position),position);
    }

    class MangGuoVideoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private MGVideoInfo mData;
        private int mPosition;

        private TextView mTvTitle;
        private ImageView mIvThumb;
        private TextView mTvSubTitle;
        private ScaleFocusListener mFocusListener;


        public MangGuoVideoViewHolder(View itemView) {
            super(itemView);

            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvThumb = itemView.findViewById(R.id.iv_thumb);
            mTvSubTitle = itemView.findViewById(R.id.tv_sub_title);
            mFocusListener = new ScaleFocusListener(ScaleFocusListener.ZOOM_FACTOR_MEDIUM,false);
            mFocusListener.onInitializeView(itemView);

            itemView.setOnFocusChangeListener(mFocusListener);
            itemView.setFocusable(true);
            itemView.setOnClickListener(this);
        }

        public void bindData(MGVideoInfo data, int position) {
            mData = data;
            mPosition = position;
            ImageLoader.loadImg(data.img,mIvThumb);
            mTvSubTitle.setText(data.subtitle);
            mTvTitle.setText(mData.title);

        }

        @Override
        public void onClick(View v) {
            ParseWebActivity.jumpTo(v.getContext(),mData.getVideoPageUrl());
        }
    }
}
