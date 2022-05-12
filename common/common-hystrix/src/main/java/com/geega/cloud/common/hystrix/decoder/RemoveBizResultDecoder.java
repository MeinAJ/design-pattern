package com.geega.cloud.common.hystrix.decoder;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Type;

/**
 * @author Jun.An3
 * @date 2022/05/10
 */
@Slf4j
public class RemoveBizResultDecoder extends AbstractDecoder {

    private static final String RESULT_FIELD_RESULT = "result";

    /**
     * 处理数据
     *
     * @param jsonObject 响应数据
     * @param type       对象类型
     * @return 处理后的响应数据
     */
    @Override
    protected Object processData(JSONObject jsonObject, Type type) {
        if (!jsonObject.containsKey(RESULT_FIELD_RESULT)) {
            return jsonObject.toJavaObject(type);
        }
        return jsonObject.getObject(RESULT_FIELD_RESULT, type);
    }

}
