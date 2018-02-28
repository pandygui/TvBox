package com.wangw.tvbox.net;

/**
 * Created by wangw on 2018/2/28.
 */

public class TvException extends Exception {

    private int mErorCode = -1;

    public TvException(String message, int erorCode) {
        super(message);
        mErorCode = erorCode;
    }

    public TvException(String message, Throwable cause, int erorCode) {
        super(message, cause);
        mErorCode = erorCode;
    }

    public TvException(Throwable cause) {
        super(cause);
    }

    public TvException(String message) {
        super(message);
    }

    public TvException(Throwable cause, int erorCode) {
        super(cause);
        mErorCode = erorCode;
    }

    public int getErorCode() {
        return mErorCode;
    }
}
