package com.geega.cloud.common.base.exception;

import com.geega.cloud.common.base.response.BizResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
@Slf4j
@ControllerAdvice(value = {"com.geega", "org.springframework.boot.autoconfigure.web.servlet.error"})
public class BizResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getParameterType() != BizResult.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return BizResult.success(body);
    }

}
