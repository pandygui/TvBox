package com.wangw.tvbox.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v17.leanback.app.VideoSupportFragment;
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerGlue;
import android.support.v17.leanback.media.PlaybackGlue;
import android.util.Log;

import com.wangw.tvbox.module.view.MyMediaPlayerGlue;
import com.wangw.tvbox.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by wangw on 2018/2/24.
 */

public class VideoPlayerFrag extends VideoSupportFragment {

    private static final String TAG = "VideoPlayerFrag";
    private String mPlayUrl;

    public static VideoPlayerFrag newInstance(String url) {

        Bundle args = new Bundle();

        VideoPlayerFrag fragment = new VideoPlayerFrag();
        args.putString("url",url);
        fragment.setArguments(args);
        return fragment;
    }

    private MyMediaPlayerGlue mTransportControlGlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = getArguments().getString("url");

        VideoSupportFragmentGlueHost glueHost =
                new VideoSupportFragmentGlueHost(VideoPlayerFrag.this);

        mTransportControlGlue = new MyMediaPlayerGlue(getActivity());
        mTransportControlGlue.setMode(MediaPlayerGlue.NO_REPEAT);
        mTransportControlGlue.setHost(glueHost);
        mTransportControlGlue.setTitle("TvBox");

        mTransportControlGlue.addPlayerCallback(
                new PlaybackGlue.PlayerCallback() {
                    @Override
                    public void onPreparedStateChanged(PlaybackGlue glue) {
                        if (glue.isPrepared()) {
                            glue.play();
                        }
                    }
                });
        mTransportControlGlue.setMyPlayerCallback(new MyMediaPlayerGlue.MyPlayerCallback() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                VideoPlayerFrag.this.onVideoSizeChanged(width,height);
            }

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (extra == MediaPlayer.MEDIA_ERROR_IO && !mPlayUrl.startsWith("https")){
                    startPlay(mPlayUrl.replace("http","https"));
                }else {
                    ToastUtils.showToast("播放失败: waht = "+what+" , extra="+extra);
                }
                return true;
            }
        });
        try {
            String decode = URLDecoder.decode(url, "UTF-8");
            startPlay(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void startPlay(String url) {
        this.mPlayUrl = url;
        Log.e(TAG, "startPlay: "+url);
        mTransportControlGlue.setArtist(url);
        mTransportControlGlue.setVideoUrl(mPlayUrl);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTransportControlGlue != null) {
            mTransportControlGlue.pause();
        }
    }
}
