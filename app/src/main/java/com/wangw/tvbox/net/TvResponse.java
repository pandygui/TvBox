package com.wangw.tvbox.net;

/**
 * Created by wangw on 2018/2/28.
 */

public class TvResponse {

    private AbstractRequestParams mRequestParams;
    private String mResult;
    private Throwable mError;

    public TvResponse() {
    }

    public TvResponse(AbstractRequestParams requestParams) {
        this.mRequestParams = requestParams;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        this.mResult = result;
    }

    public Throwable getError() {
        return mError;
    }

    public void setError(String error) {
        this.mError = new TvException(error);
    }

    public void setError(Throwable error) {
        mError = error;
    }

    public AbstractRequestParams getRequestParams() {
        return mRequestParams;
    }
}
