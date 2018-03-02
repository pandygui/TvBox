package com.wangw.tvbox.mangguo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.mangguo.model.ChannelInfo;
import com.wangw.tvbox.module.FocusSearchLinearLayout;
import com.wangw.tvbox.module.FocusSearchListener;
import com.wangw.tvbox.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoChannelView extends RelativeLayout {

    private static final String TAG = "MangGuoChannelView";
    @BindView(R.id.ll_parmas)
    FocusSearchLinearLayout mLlParmas;
    @BindView(R.id.params)
    ScrollView mParams;
    @BindView(R.id.channels)
    ScrollView mChannels;
    private FocusSearchLinearLayout mLlChannelList;
    private List<ChannelInfo> mChannelList;
    private ChannelListener mChannelListener;
    private TextView mSelectedView;

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
        inflate(getContext(), R.layout.view_mangguo_channel, this);
        ButterKnife.bind(this);
        mLlChannelList = findViewById(R.id.ll_channel);
        mLlChannelList.setListener(new FocusSearchListener() {
            @Override
            public View focusSearch(View focused, int direction) {
                if (mLlChannelList.getChildCount() > 0 && direction == FOCUS_DOWN && mLlChannelList.indexOfChild(focused) == mLlChannelList.getChildCount() - 1)
                    return mLlChannelList.getChildAt(0);
                else
                    return null;
            }
        });
    }

    public void setData(List<ChannelInfo> list) {
        this.mChannelList = list;
        if (list == null || list.isEmpty()) {
            mLlChannelList.removeAllViews();
        }
        initChannelListView();
    }

    private void initChannelListView() {
        mLlChannelList.removeAllViews();
        addChannelItemView("筛选").setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                mParams.setVisibility(VISIBLE);
//                mChannels.setVisibility(GONE);
                ToastUtils.showToast("正在努力开发中。。。");

            }
        });
        addChannelItemView("搜索").setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), MangGuoSearchActivity.class));
            }
        });
        for (ChannelInfo channelInfo : mChannelList) {
            TextView view = addChannelItemView(channelInfo.channelName);
            view.setTag(channelInfo);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedView((TextView) v);
                    if (mChannelListener != null) {
                        mChannelListener.setChannel((ChannelInfo) v.getTag());
                    }
                }
            });
            if (mSelectedView == null) {
                setSelectedView(view);
            }
        }


    }

    private TextView addChannelItemView(String title) {
        TextView view = new TextView(getContext());
        view.setFocusable(true);
        view.setText(title);
        view.setPadding(0, 20, 0, 20);
        view.setGravity(Gravity.CENTER);
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundColor(getResources().getColor(R.color.channel_focused));
                } else {
                    v.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        view.setTextColor(Color.WHITE);
        view.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLlChannelList.addView(view, params);
        return view;
    }

    private void setSelectedView(TextView tv) {
        if (tv == mSelectedView) {
            return;
        }
        if (mSelectedView != null) {
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

    public interface ChannelListener {
        void setChannel(ChannelInfo info);
    }

}
