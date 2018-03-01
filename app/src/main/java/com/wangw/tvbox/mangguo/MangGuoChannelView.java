package com.wangw.tvbox.mangguo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.utils.ToastUtils;

import java.util.List;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoChannelView extends RelativeLayout {

    private LinearLayout mLlChannelList;
    private List<ChannelInfo> mChannelList;
    private ChannelListener mChannelListener;
    private TextView mSelectedView;
    private TextView mSearchView;

    public MangGuoChannelView(Context context) {
        super(context);
        initView();
    }

    public MangGuoChannelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MangGuoChannelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_mangguo_channel,this);
        mLlChannelList = findViewById(R.id.ll_channel);
    }

    public void setData(List<ChannelInfo> list){
        this.mChannelList = list;
        if (list == null || list.isEmpty()){
            mLlChannelList.removeAllViews();
        }
        initChannelListView();
    }

    private void initChannelListView() {
        mLlChannelList.removeAllViews();
        for (ChannelInfo channelInfo : mChannelList) {
            TextView view = getChannelItemView();
            view.setText(channelInfo.channelName);
            view.setTag(channelInfo);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedView((TextView) v);
                    if (mChannelListener != null){
                        mChannelListener.setChannel((ChannelInfo) v.getTag());
                    }
                }
            });
            if (mSelectedView == null){
                setSelectedView(view);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            mLlChannelList.addView(view, params);
        }

        this.mSearchView = getChannelItemView();
        mSearchView.setText("搜索");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        mLlChannelList.addView(mSearchView, params);
        mSearchView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("搜索");
            }
        });

    }

    private TextView getChannelItemView(){
        TextView view = new TextView(getContext());
        view.setFocusable(true);
        view.setPadding(0,30,0,30);
        view.setGravity(Gravity.CENTER);
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    v.setBackgroundColor(getResources().getColor(R.color.channel_focused));
                }else {
                    v.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        view.setTextColor(Color.WHITE);
        view.setTextSize(18);
        return view;
    }

    private void setSelectedView(TextView tv) {
        if (tv == mSelectedView){
            return;
        }
        if (mSelectedView != null){
            mSelectedView.getPaint().setFakeBoldText(false);
            mSelectedView.setTextColor(Color.WHITE);
        }
        tv.setTextColor(getResources().getColor(R.color.channel_selected));
        tv.getPaint().setFakeBoldText(true);
        mSelectedView = tv;
    }

    public ChannelListener getChannelListener() {
        return mChannelListener;
    }

    public void setChannelListener(ChannelListener channelListener) {
        mChannelListener = channelListener;
    }

    public interface ChannelListener{
        void setChannel(ChannelInfo info);
    }

}
