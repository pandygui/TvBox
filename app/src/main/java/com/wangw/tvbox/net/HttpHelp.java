package com.wangw.tvbox.net;

import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.request.UriRequest;
import org.xutils.x;

import java.lang.reflect.Type;

/**
 * Created by wangw on 2018/2/28.
 */

public class HttpHelp {

    public static Callback.Cancelable post(final AbstractRequestParams requestParams, final TvRequestCallback callback){
        return x.http().post(requestParams, new Callback.CommonCallback<String>() {
            private TvResponse mResponse = new TvResponse(requestParams);
            @Override
            public void onSuccess(String result) {
                mResponse.setResult(result);
                callback.onSuccess(mResponse);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mResponse.setError(ex);
                callback.onError(mResponse);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancel(mResponse);
            }

            @Override
            public void onFinished() {
                callback.onFinish(mResponse);
            }
        });
    }

    public static Callback.Cancelable get( AbstractRequestParams requestParams, TvRequestCallback callback){
        return x.http().get(requestParams, new TVCallback(requestParams,callback));
    }


    static class TVCallback implements Callback.TypedCallback<String>, RequestInterceptListener{

        private static final String TAG = "";
        private TvRequestCallback callback;
        private AbstractRequestParams requestParams;
        private TvResponse mResponse;

        public TVCallback(AbstractRequestParams requestParams, TvRequestCallback callback) {
            this.callback = callback;
            this.requestParams = requestParams;
            mResponse = new TvResponse(requestParams);
        }

        @Override
        public void onSuccess(String result) {
            mResponse.setResult(result);
            callback.onSuccess(mResponse);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            mResponse.setError(ex);
            callback.onError(mResponse);
        }

        @Override
        public void onCancelled(CancelledException cex) {
            callback.onCancel(mResponse);
        }

        @Override
        public void onFinished() {
            callback.onFinish(mResponse);
        }

        @Override
        public void beforeRequest(UriRequest request) throws Throwable {
            Log.d(TAG, "beforeRequest: ");
        }

        @Override
        public void afterRequest(UriRequest request) throws Throwable {
            Log.d(TAG, "afterRequest: ");
//            String charset = "UTF-8";
//            InputStream in = request.getInputStream();
//            if (!(in instanceof BufferedInputStream)) {
//                in = new BufferedInputStream(in);
//            }
//            Reader reader = new InputStreamReader(in,charset);
//            BufferedReader br = new BufferedReader(reader);
//            StringBuilder sb = new StringBuilder();
//            String tmp;
//            while ((tmp = br.readLine()) != null){
//                Log.d(TAG, "afterRequest: tmp = "+tmp);
//                sb.append(tmp);
//            }
//            char[] buf = new char[1024];
//            int len;
//            while ((len = reader.read(buf)) >= 0) {
//                sb.append(buf, 0, len);
//            }
//            Log.d(TAG, "afterRequest: "+sb.toString());
        }

        @Override
        public Type getLoadType() {
//            return BaseMGModel.class;
            return String.class;
        }
    }

}
