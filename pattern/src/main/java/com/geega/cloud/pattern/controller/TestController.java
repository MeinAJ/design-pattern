package com.geega.cloud.pattern.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * @author Jun.An3
 * @date 2022/05/11
 */
@RestController
@RequestMapping("/api/v1/test")
@Api(value = "测试Controller")
public class TestController {

    @GetMapping("/info")
    @ApiOperation("信息接口")
    public String info() {
        return "info";
    }

}
