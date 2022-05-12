package com.geega.cloud.common.hystrix.fallback;

import feign.hystrix.FallbackFactory;

/**
 * 默认fallback工厂
 *
 * @author Jun.An3
 * @date 2022/05/12
 */
public class DefaultFallbackFactory implements FallbackFactory<Object> {

    @Override
    public Object create(Throwable throwable) {
        throw new IllegalStateException(throwable);
    }

}
