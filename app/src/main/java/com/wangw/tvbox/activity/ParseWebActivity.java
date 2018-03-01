package com.wangw.tvbox.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wangw.tvbox.TvApp;
import com.wangw.tvbox.fragment.VideoPlayerFrag;

/**
 * Created by wangw on 2018/2/24.
 */

public class ParseWebActivity extends BaseActivity {

    private String[] medias = {".mp4",".m3u8",".flv",".3gp",".rmvb"};
    private static final String TAG = "ParseWebActivity";
    private String mVideoUrl;
    private String mVideoPage;
    private AlertDialog mDialog;

    public static void jumpTo(Context context, String url){
        Intent intent = new Intent(context, ParseWebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoPage = getIntent().getStringExtra("url");
        doParser();


    }

    private void doParser() {
        showLoading(true,"解析中...");
        final WebView webView = new WebView(this);
        WebSettings st = webView.getSettings();
        st.setJavaScriptEnabled(true);
        st.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d(TAG, "shouldOverrideUrlLoading: "+request.getUrl());

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.d(TAG, "shouldInterceptRequest: "+request.getUrl());
                if (isMedia(request.getUrl().toString())){
                    mVideoUrl = request.getUrl().toString();
                    TvApp.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeLoading();
                            startPlay(mVideoUrl);
                            webView.goBack();
                            webView.clearView();
                            webView.onPause();
                        }
                    });

                }
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "onPageFinished: "+url);
                webView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkVideoUrl();
                    }
                },500);
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl("http://www.82190555.com/video.php?url="+mVideoPage);
    }

    private void checkVideoUrl() {
        if (isCancel())
            return;
        closeLoading();
        if (TextUtils.isEmpty(mVideoUrl)) {
            if (mDialog == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                this.mDialog = builder.setMessage("解析失败，是否刷新重试！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .setPositiveButton("刷新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(mVideoUrl))
                                    doParser();
                                dialog.dismiss();
                            }
                        })
                        .show();
            }else {
                mDialog.show();
            }
            mDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    .requestFocus();
        }
    }

    private void startPlay(String url) {
        if (isCancel())
            return;
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
        VideoPlayerFrag fragment = VideoPlayerFrag.newInstance(url);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }

    private boolean isMedia(String url){
        for (String media : medias) {
            if (url.contains(media))
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
