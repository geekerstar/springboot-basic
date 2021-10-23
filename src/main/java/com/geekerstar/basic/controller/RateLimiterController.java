package com.geekerstar.basic.controller;

import cn.hutool.core.lang.Dict;
import com.geekerstar.basic.annotation.GuavaRateLimiter;
import com.geekerstar.basic.annotation.RedisRateLimiter;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2021/8/18 22:07
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/limit")
@ApiSupport(order = 3)
public class RateLimiterController {

    @RedisRateLimiter(value = 5)
    @GetMapping("/redisTest1")
    public Dict redisTest1() {
        log.info("【test1】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
    }

    @GetMapping("/redisTest2")
    public Dict redisTest2() {
        log.info("【test2】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "我一直都在，卟离卟弃");
    }

    @RedisRateLimiter(value = 2, key = "测试自定义key")
    @GetMapping("/redisTest3")
    public Dict redisTest3() {
        log.info("【test3】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
    }

    @GuavaRateLimiter(value = 1.0, timeout = 300)
    @GetMapping("/guavaTest1")
    public Dict guavaTest1() {
        log.info("【test1】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
    }

    @GetMapping("/guavaTest2")
    public Dict guavaTest2() {
        log.info("【test2】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "我一直都在，卟离卟弃");
    }

    @GuavaRateLimiter(value = 2.0, timeout = 300)
    @GetMapping("/guavaTest3")
    public Dict guavaTest3() {
        log.info("【test3】被执行了。。。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
    }
}
