package com.geega.cloud.common.base.exception;

import com.geega.cloud.common.base.response.error.IBizError;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
public class BizException extends RuntimeException implements IBizError {

    private final String code;

    public BizException(IBizError iBizError) {
        super(iBizError.getMsg());
        this.code = iBizError.getCode();
    }

    public BizException(IBizError iBizError, Throwable throwable) {
        super(iBizError.getMsg(), throwable);
        this.code = iBizError.getCode();
    }

    public BizException(IBizError iBizError, Object... args) {
        super(formatMsg(iBizError.getMsg(), args));
        this.code = iBizError.getCode();
    }

    public BizException(IBizError iBizError, Throwable throwable, Object... args) {
        super(formatMsg(iBizError.getMsg(), args), throwable);
        this.code = iBizError.getCode();
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(String code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.code = code;
    }

    public BizException(String code, String msg, Object... args) {
        super(formatMsg(msg, args));
        this.code = code;
    }

    public BizException(String code, String msg, Throwable throwable, Object... args) {
        super(formatMsg(msg, args), throwable);
        this.code = code;
    }

    private static String formatMsg(String msg, Object... args) {
        if (msg == null) {
            return null;
        }
        return String.format(msg, args);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return super.getMessage();
    }

}
