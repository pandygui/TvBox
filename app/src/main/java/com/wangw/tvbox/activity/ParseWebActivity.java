package com.wangw.tvbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public static void jumpTo(Context context, String url){
        Intent intent = new Intent(context, ParseWebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoading(true,"解析中...");
        String url = getIntent().getStringExtra("url");
        doParser(url);


    }

    private void doParser(String url) {
        final WebView webView = new WebView(this);
        WebSettings st = webView.getSettings();
        st.setJavaScriptEnabled(true);
        st.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);

        webView.setWebViewClient(new WebViewClient(){
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
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl(url);
    }

    private void startPlay(String url) {
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
}
