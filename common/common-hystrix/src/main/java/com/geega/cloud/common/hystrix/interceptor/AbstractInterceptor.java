package com.geega.cloud.common.hystrix.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Collection;

/**
 * 抽象拦截器,只做了日志打印,如果想定制处理,自己类去处理
 *
 * @author Jun.An3
 * @date 2022/04/27
 */
@Slf4j
public abstract class AbstractInterceptor implements RequestInterceptor {

    private static final String NONE = "NONE";

    private static final int FIVE_KB = 5 * 1024;

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    /**
     * 功能:只实现了打印日志,需要扩展定制化的需求,实现该抽象类去扩展
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //扩展方法
        extendProcess(requestTemplate);
        //打印日志
        log.info("第三方:{},接口:{}{},方法:{},消息体:{}",
                getClientName(),
                requestTemplate.feignTarget().url(),
                requestTemplate.url(),
                requestTemplate.method(),
                getBody(requestTemplate));
    }

    /**
     * 第三方客户端名字
     */
    protected abstract String getClientName();

    /**
     * 扩展处理方法,比如自己可以去设置一些header
     *
     * @param requestTemplate 请求类
     */
    protected abstract void extendProcess(RequestTemplate requestTemplate);

    private String getBody(RequestTemplate requestTemplate) {
        //如果是上传文件直接忽略不打印
        final Collection<String> requestVariables = requestTemplate.getRequestVariables();
        if (!requestVariables.isEmpty() && requestVariables.contains(APPLICATION_OCTET_STREAM)) {
            return NONE;
        }
        final byte[] body = requestTemplate.body();
        if (body != null && body.length > 0) {
            //只打印最多5kb的数据,如果body是文件,那么一般为乱码,不管它
            return new String(body, 0, Math.min(body.length, FIVE_KB));
        }
        return NONE;
    }

    protected void header(RequestTemplate requestTemplate, String name, String... values) {
        if (!requestTemplate.headers().containsKey(name)) {
            requestTemplate.header(name, values);
        }
    }

    private String getVariables(RequestTemplate requestTemplate) {
        final Collection<String> requestVariables = requestTemplate.getRequestVariables();
        if (!requestVariables.isEmpty()) {
            return Arrays.toString(requestVariables.toArray());
        }
        return NONE;
    }

}
