package com.wangw.tvbox.samples;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wangw.tvbox.R;
import com.wangw.tvbox.activity.BaseActivity;
import com.wangw.tvbox.test.TestAdapter;
import com.wangw.tvbox.test.TestFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangw on 2018/2/27.
 */

public class TestActivity extends BaseActivity implements TestFrameLayout.OnFocusSearchListener {


    private RecyclerView mItems;
    private LinearLayout mLlTitle;
    private TestFrameLayout mRootView;
    private ScrollView mScrollView;
    private View mLLOptions;
    private TestAdapter mAdapter;
    private List<String> mDatas;
    private String mTitle = "Title-0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mItems = findViewById(R.id.items);
        mLlTitle = findViewById(R.id.ll_title);
        mRootView = findViewById(R.id.rootview);
        mLLOptions = findViewById(R.id.ll_options);
        mScrollView = findViewById(R.id.scrollview);
        mLLOptions.setOnFocusChangeListener(new TestFocuseListener());
        findViewById(R.id.tv_news).setOnFocusChangeListener(new TestFocuseListener());
        findViewById(R.id.tv_hots).setOnFocusChangeListener(new TestFocuseListener());
        mRootView.setListener(this);

        initTitle();
        initRecyclerView();


    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        mItems.setLayoutManager(layoutManager);
        mAdapter = new TestAdapter();
        mItems.setAdapter(mAdapter);
        mDatas = new ArrayList<>(40);
        for (int i = 0; i < 40; i++) {
            mDatas.add("item-"+i);
        }
        mAdapter.setList(mDatas,mTitle);
    }

    private void initTitle() {
        for (int i = 0; i < 10; i++) {
            TextView btn = new TextView(this);
            btn.setText("Title-"+i);
            btn.setFocusable(true);
            btn.setTextColor(Color.BLACK);
            btn.setOnFocusChangeListener(new TestFocuseListener());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView b = (TextView) v;
                    mTitle = b.getText().toString();
                    showToast("onClick "+ mTitle);
                    mAdapter.setList(mDatas,mTitle);
                }
            });
            mLlTitle.addView(btn,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public View focusSearch(View focused, int direction) {

        if (direction == View.FOCUS_UP && mRootView.getFocusedChild() == mScrollView){
//            int i = mLlTitle.indexOfChild(mLlTitle.getFocusedChild());
//            i++;
//            if (i >= mLlTitle.getChildCount()){
//                i = 0;
//            }
//            return mLlTitle.getChildAt(i);

            mLLOptions.setVisibility(View.VISIBLE);
            return mLLOptions;
        }

        if (mRootView.getFocusedChild() == mScrollView){
            mLLOptions.setVisibility(View.INVISIBLE);
        }


        return null;
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        focused.setBackgroundColor(Color.RED);
    }

    @Override
    public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return false;
    }

    @Override
    public boolean onKey(View rootView, int keyCode, KeyEvent event) {
        return false;
    }
}
