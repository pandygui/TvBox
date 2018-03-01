package com.wangw.tvbox.mangguo.database;

import android.util.Log;

import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.loader.Loader;
import org.xutils.http.request.UriRequest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by wangw on 2018/3/1.
 */

public class MangGuoLoader extends Loader<String> {

    private static final String TAG = "MangGuoLoader";
    private String charset = "UTF-8";
    private String resultStr = null;

    @Override
    public Loader<String> newInstance() {
        return new MangGuoLoader();
    }

    @Override
    public String load(InputStream in) throws Throwable {
        if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream(in);
        }
        Reader reader = new InputStreamReader(in,charset);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String tmp;
        while ((tmp = br.readLine()) != null){
            Log.d(TAG, "afterRequest: tmp = "+tmp);
            sb.append(tmp);
        }
        return sb.toString();
    }

    @Override
    public String load(UriRequest request) throws Throwable {
        request.sendRequest();
        return this.load(request.getInputStream());
    }

    @Override
    public String loadFromCache(DiskCacheEntity cacheEntity) throws Throwable {
        if (cacheEntity != null) {
            return cacheEntity.getTextContent();
        }

        return null;
    }

    @Override
    public void save2Cache(UriRequest request) {
        saveStringCache(request, resultStr);
    }
}
