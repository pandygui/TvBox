package com.wangw.tvbox.module;

import android.support.v17.leanback.widget.ShadowOverlayContainer;
import android.support.v17.leanback.widget.ShadowOverlayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangw.tvbox.TvApp;

/**
 * Created by wangw on 2018/3/1.
 */

public abstract class TvAdapter<V extends TvViewHolder,T> extends BaseAdapter<V,T> {

    private ShadowOverlayHelper mShadowOverlayHelper;

    public TvAdapter() {
        mShadowOverlayHelper = new ShadowOverlayHelper.Builder()
                .needsOverlay(true)
                .needsShadow(false)
                .needsRoundedCorner(true)
                .preferZOrder(true)
                .keepForegroundDrawable(true)
                .options(ShadowOverlayHelper.Options.DEFAULT)
                .build(TvApp.getAppContext());
        setHasStableIds(true);
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        ShadowOverlayContainer wraper = mShadowOverlayHelper.createShadowOverlayContainer(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayResult(viewType),null);
        wraper.wrap(view);
        return createViewHoler(wraper);
    }

    protected abstract V createViewHoler(ShadowOverlayContainer wraper);

    protected abstract int getLayResult(int viewType);

    @Override
    public void onBindViewHolder(V holder, int position) {
        holder.bindData(getData(position), position);
    }
}
