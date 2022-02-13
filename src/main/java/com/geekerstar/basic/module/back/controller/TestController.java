package com.geekerstar.basic.module.back.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author geekerstar
 * @date 2021/8/14 19:07
 * @description
 */
@Slf4j
@Controller
@RequestMapping("/test")
@Api(tags = "测试接口")
@ResponseBody
@ApiSupport(order = 7)
public class TestController {

    @GetMapping("/1")
    public String test1() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.trace("trace");
        return "dadasd";
    }
}
