package com.wangw.tvbox.net;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.wangw.tvbox.utils.JsonUtils;

/**
 * Created by wangw on 2018/2/28.
 */

public abstract class JsonRequestCallback implements TvRequestCallback {


    private static final String TAG = "JsonRequestCallback";

    @Override
    public void onSuccess(TvResponse response) {
        log(response);
        if (TextUtils.isEmpty(response.getResult())){
            response.setError("Response 为空！！！");
            onError(response);
            return;
        }
        TypeReference typeReference = getJsonClz();
        if (typeReference != null) {
            try {
                Object o =  JsonUtils.json2Object(response.getResult(), typeReference);
                onSuccess(response,o);
            }catch (Exception e){
                Log.e(TAG, "[解析Response异常]: ", e);
                response.setError("Json解析异常:"+e.getMessage());
                if (response.getRequestParams() != null) {
                    response.setError(response.getError().getMessage()+"\n url="+response.getRequestParams().getUri().toString());
                }
                onError(response);
            }
        }else {
            onSuccess(response,response.getResult());
        }
    }

    private void log(TvResponse response) {
        Log.d(TAG, " =====================>");
        StringBuilder sb = new StringBuilder();
        sb.append("[request] ");
        if (response.getRequestParams() != null)
            sb.append(response.getRequestParams());
        sb.append("\n[response] ")
                .append(response.getResult());
        Log.d(TAG, sb.toString());
        Log.d(TAG, "<============================");
    }

    protected abstract void onSuccess(TvResponse response,Object obj);

    @Override
    public void onFinish(TvResponse response) {

    }

    @Override
    public void onCancel(TvResponse response) {

    }

    protected abstract TypeReference getJsonClz();
}
