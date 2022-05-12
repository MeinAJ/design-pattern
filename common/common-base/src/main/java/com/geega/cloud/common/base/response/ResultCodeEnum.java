package com.geega.cloud.common.base.response;

import com.geega.cloud.common.base.response.error.IBizError;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
public enum ResultCodeEnum implements IBizError {
    /**
     * 系统code
     */
    SUCCESS("0000", "success"),
    SYSTEM_ERROR("1000", "系统异常"),
    GENERATE_ID_FAIL("1001", "生成id失败"),
    BUSY("1002", "业务繁忙，请稍后重试"),
    ;

    private String code;

    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
