package com.geega.cloud.common.hystrix.interceptor;

import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象拦截器,只做了日志打印,如果想定制处理,自己类去处理
 *
 * @author Jun.An3
 * @date 2022/04/27
 */
@Slf4j
public abstract class DefaultAbstractInterceptor extends AbstractInterceptor {

    @Override
    protected void extendProcess(RequestTemplate requestTemplate) {
        //自己实现类去实现
    }

}
