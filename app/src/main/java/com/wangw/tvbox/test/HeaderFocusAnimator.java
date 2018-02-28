package com.wangw.tvbox.test;

import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;

/**
 * Created by wangw on 2018/2/28.
 */

public class HeaderFocusAnimator extends FocusAnimator {

    ItemBridgeAdapter.ViewHolder mViewHolder;
    HeaderFocusAnimator(View view, float scale, int duration) {
        super(view, scale, false, duration);

        ViewParent parent = view.getParent();
        while (parent != null) {
            if (parent instanceof RecyclerView) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent != null) {
            mViewHolder = (ItemBridgeAdapter.ViewHolder) ((RecyclerView) parent)
                    .getChildViewHolder(view);
        }
    }

    @Override
    void setFocusLevel(float level) {
        Presenter presenter = mViewHolder.getPresenter();
        if (presenter instanceof RowHeaderPresenter) {
            ((RowHeaderPresenter) presenter).setSelectLevel(
                    ((RowHeaderPresenter.ViewHolder) mViewHolder.getViewHolder()), level);
        }
        super.setFocusLevel(level);
    }
}
