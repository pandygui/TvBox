package com.wangw.tvbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wangw.tvbox.R;
import com.wangw.tvmouse.TvMouseActivity;

/**
 * Created by wangw on 2018/2/24.
 */

public class WebActivity extends TvMouseActivity {

    private WebView mWebView;

    
    public static void jumpTo(Context context,String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.web);
        setScrollTargetView(mWebView);
        showCursor();

        initWebView();

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpVideo();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    private void jumpVideo() {
        findViewById(R.id.btn_play).setEnabled(false);
        ParseWebActivity.jumpTo(WebActivity.this,mWebView.getUrl());
        findViewById(R.id.btn_play).setEnabled(true);
    }

    private void initWebView() {
        WebSettings st = mWebView.getSettings();
        st.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        st.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){});
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                super.onUnhandledKeyEvent(view, event);
            }
        });

        mWebView.loadUrl("https://www.mgtv.com/");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP){
            jumpVideo();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView = null;
    }
}
