package com.wangw.tvbox.fragment;

import android.os.Bundle;
import android.support.v17.leanback.app.VideoSupportFragment;
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerGlue;
import android.support.v17.leanback.media.PlaybackGlue;

import com.wangw.tvbox.module.MyMediaPlayerGlue;

/**
 * Created by wangw on 2018/2/24.
 */

public class VideoPlayerFrag extends VideoSupportFragment {

    private static final String TAG = "VideoPlayerFrag";

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
        mTransportControlGlue.setTitle("");
        mTransportControlGlue.setArtist(url);
        mTransportControlGlue.addPlayerCallback(
                new PlaybackGlue.PlayerCallback() {
                    @Override
                    public void onPreparedStateChanged(PlaybackGlue glue) {
                        if (glue.isPrepared()) {
                            glue.play();
                        }
                    }
                });
//        mTransportControlGlue.setMyPlayerCallback(new MyMediaPlayerGlue.MyPlayerCallback() {
//            @Override
//            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                VideoPlayerFrag.this.onVideoSizeChanged(width,height);
//            }
//        });
        mTransportControlGlue.setVideoUrl(url);//("http://disp.titan.mgtv.com/vod.do?fmt=4&pno=2010&fid=C0EAFBB7667154C8850804E7D2F91922&file=%2Fc1%2F2018%2F02%2F13_0%2FC0EAFBB7667154C8850804E7D2F91922_20180213_1_1_840.mp4");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTransportControlGlue != null) {
            mTransportControlGlue.pause();
        }
    }
}
