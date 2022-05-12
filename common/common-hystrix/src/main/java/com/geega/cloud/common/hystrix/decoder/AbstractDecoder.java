package com.geega.cloud.common.hystrix.decoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 通用openfeign的解码处理抽象类
 *
 * @author Jun.An3
 * @date 2022/05/06
 */
@Slf4j
public abstract class AbstractDecoder implements Decoder {

    private static final String CODE = "code";

    private static final String SUCCESS_CODE = "0000";

    private static final String MSG = "msg";

    public boolean isCheckResultCode() {
        return true;
    }

    public String getFieldCode() {
        return CODE;
    }

    public String getSuccessCode() {
        return SUCCESS_CODE;
    }

    public String getFieldMsg() {
        return MSG;
    }

    /**
     * 空JSONObject
     */
    protected static final JSONObject NONE_JSON_OBJECT = new JSONObject();

    private String getMessage(JSONObject jsonObject) {
        return jsonObject.getString(getFieldMsg());
    }

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {
        //获取响应数据
        JSONObject responseData = getResponseData(response);
        //检查响应状态
        checkStatus(responseData);
        // 处理空数据
        if (Objects.isNull(type)) {
            return null;
        }
        //处理数据
        return processData(responseData, type);
    }

    /**
     * 处理数据
     *
     * @param jsonObject 响应数据
     * @param type       对象类型
     * @return 处理后的响应数据
     */
    protected abstract Object processData(JSONObject jsonObject, Type type);

    /**
     * 获取响应数据
     */
    private JSONObject getResponseData(Response response) throws IOException {
        //打印响应日志
        log.info("第三方请求完成,状态:{},接口:{},方法:{}", response.status(), response.request().url(), response.request().httpMethod());
        if (response.body() == null) {
            return NONE_JSON_OBJECT;
        }
        String resultStr = IOUtil.toString(response.body().asInputStream(), StandardCharsets.UTF_8.toString());
        log.info("响应体内容:{}", resultStr);
        return JSON.parseObject(resultStr);
    }

    /**
     * 检查响应状态
     */
    private void checkStatus(JSONObject jsonObject) {
        if (!isCheckResultCode()) {
            return;
        }
        if (jsonObject == null || !jsonObject.containsKey(getFieldCode())) {
            return;
        }
        String code = jsonObject.getString(getFieldCode());
        if (!getSuccessCode().equalsIgnoreCase(code)) {
            log.error("状态码校验失败，期望状态码: {}, 返回状态码: {}", getSuccessCode(), code);
            throw new IllegalStateException(getMessage(jsonObject));
        }
    }

}
