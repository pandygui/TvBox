package com.wangw.tvbox.module.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangw.tvbox.R;


/**
 * Created by wangw on 2017/2/15.
 */
// TODO: 4/23/17 用dialog不如一个直接的View吧
public class LoadingDialog extends Dialog {

    private TextView mTvHint;
    private String mHint;
    private ImageView mNewLoading;

    public interface OnDialogBack {
        void onBackPressed();
    }

    private OnDialogBack mDialogBack;

    public void setDialogBack(OnDialogBack mDialogBack) {
        this.mDialogBack = mDialogBack;
    }

    public LoadingDialog(Context context) {
        super(context, R.style.style_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitView();
    }

    private void onInitView() {
        setContentView(R.layout.dialog_loading);
        mTvHint = (TextView) findViewById(R.id.tv_hint);
        mNewLoading = ((ImageView) findViewById(R.id.new_loading));
        if (!TextUtils.isEmpty(mHint))
            mTvHint.setText(mHint);
    }

    public void setHintText(String hint) {
        if (mTvHint != null)
            mTvHint.setText(hint);
        mHint = hint;
    }



    public void setShowNewLoading(){
        if (mNewLoading != null){
            mNewLoading.setVisibility(View.VISIBLE);
            AnimationDrawable drawable = (AnimationDrawable) getContext().getResources().getDrawable(R.drawable.loading_animation);
            mNewLoading.setImageDrawable(drawable);
            drawable.start();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDialogBack != null) {
            mDialogBack.onBackPressed();
        }
    }
}
