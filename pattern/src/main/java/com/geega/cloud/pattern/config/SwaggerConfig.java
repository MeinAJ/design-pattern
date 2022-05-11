package com.geega.cloud.pattern.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author Jun.An3
 * @date 2022/05/11
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableOpenApi
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"knife4j.enable"}, matchIfMissing = true)
public class SwaggerConfig {

    /**
     * API分组
     */
    @Bean(value = "swaggerApiGroup")
    public Docket indexApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API分组")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.geega.cloud.pattern.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微服务Demo")
                .description("搭建一个灵活的框架demo")
                .termsOfServiceUrl("www.baidu.com")
                .contact(new Contact("微服务Demo", "www.baidu.com", "learning@163.com"))
                .version("1.0")
                .build();
    }

}