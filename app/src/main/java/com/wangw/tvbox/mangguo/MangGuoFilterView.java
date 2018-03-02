package com.wangw.tvbox.mangguo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.module.FocusSearchListener;
import com.wangw.tvbox.module.view.FocusSearchLinearLayout;

import java.util.List;

/**
 * Created by wangw on 2018/3/3.
 */

public class MangGuoFilterView extends LinearLayout {


    private MGFilterListener mListener;

    public MangGuoFilterView(Context context) {
        super(context);
        onInitView();
    }

    public MangGuoFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onInitView();
    }

    public MangGuoFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitView();
    }

    private void onInitView() {
        setOrientation(VERTICAL);
    }

    public void setData(List<ChannelInfo.ItemsBeanXX.ItemsBeanX> items){
        removeAllViews();
        if (items == null || items.isEmpty())
            return;
        for (ChannelInfo.ItemsBeanXX.ItemsBeanX item : items) {
            if (item.items == null || item.items.isEmpty())
                continue;
            FilterItemContainer view = new FilterItemContainer(getContext(), item);
            addView(view,new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }



    class FilterItemContainer extends HorizontalScrollView implements OnClickListener, OnFocusChangeListener {

        private  FocusSearchLinearLayout mContainer;
        private TextView mSelectedView;
        private ChannelInfo.ItemsBeanXX.ItemsBeanX mItem;

        public FilterItemContainer(Context context,ChannelInfo.ItemsBeanXX.ItemsBeanX item) {
            super(context);
            this.mItem = item;
            this.mContainer = new FocusSearchLinearLayout(getContext());
            mContainer.setPadding(20,10,20,10);
            mContainer.setListener(new FocusSearchListener() {
                @Override
                public View focusSearch(View focused, int direction) {
                    if (direction == FOCUS_RIGHT && mContainer.indexOfChild(focused) == mContainer.getChildCount()-1){
                        return mContainer.getChildAt(0);
                    }
                    return null;
                }
            });
            mContainer.setOrientation(HORIZONTAL);
            addView(mContainer,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            setHorizontalScrollBarEnabled(false);

            for (int i = 0; i < item.items.size(); i++) {
                TextView view = addFilterItem(item.items.get(i));
                if (i == 0 && !TextUtils.equals("sort",item.eName)){
                    setSelectedView(view);
                }
                mContainer.addView(view,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));//120,80));//
            }

        }

        TextView addFilterItem(ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean bean){
            TextView view = new TextView(getContext());
            view.setFocusable(true);
            view.setText(bean.tagName);
            view.setTag(bean);
            view.setTextSize(20);
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            view.setOnClickListener(this);
            view.setPadding(20,15,20,15);
            view.setOnFocusChangeListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {
            setSelectedView((TextView) v);
        }

        private void setSelectedView(TextView tv) {
            if (tv == mSelectedView) {
                return;
            }
            setFilter(mItem, (ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean) tv.getTag());
            if (mSelectedView != null) {
                setSelectedState(mSelectedView,false);
            }
            setSelectedState(tv,true);
            mSelectedView = tv;
        }

        private void setSelectedState(TextView tv,boolean isSelected) {
            if (isSelected) {
                tv.setTextColor(getResources().getColor(R.color.channel_selected));
                tv.getPaint().setFakeBoldText(true);
            }else {
                tv.getPaint().setFakeBoldText(false);
                tv.setTextColor(Color.WHITE);
            }
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                v.setBackgroundColor(getResources().getColor(R.color.channel_focused));
            }else {
                v.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    private void setFilter(ChannelInfo.ItemsBeanXX.ItemsBeanX item, ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean bean) {
        if (mListener != null){
            mListener.setFilter(item,bean);
        }
    }

    public MGFilterListener getListener() {
        return mListener;
    }

    public void setListener(MGFilterListener listener) {
        mListener = listener;
    }

    public interface MGFilterListener{
        void setFilter(ChannelInfo.ItemsBeanXX.ItemsBeanX item, ChannelInfo.ItemsBeanXX.ItemsBeanX.ItemsBean bean);
    }

}
