package com.geega.cloud.common.hystrix.decoder;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Type;

/**
 * 通用openfeign的响应拦截器处理
 *
 * @author Jun.An3
 * @date 2022/05/06
 */
@Slf4j
public class DefaultDecoder extends AbstractDecoder {

    @Override
    public Object processData(JSONObject jsonObject, Type type) {
        return jsonObject.toJavaObject(type);
    }

}
