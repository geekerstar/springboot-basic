package com.geekerstar.basic.runner;

import com.geekerstar.basic.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * @date 2021/8/14 18:23
 * @description
 */
@Slf4j
@Component
@RequiredArgsConstructor
//@Order(1) 数字越小越先，Order(1)>Order(2)>不加
public class StartedUpRunner implements ApplicationRunner {

    @Value("${server.port:8888}")
    private String port;
    private final ConfigurableApplicationContext context;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        try {
            redisTemplate.hasKey("dispatcher");
        } catch (Exception e) {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            context.close();
        }
        if (context.isActive()) {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            String url = String.format("http://%s:%s%s", String.valueOf(CommonUtil.getInet4Address()).replace("/", ""), port, "/doc.html");
            log.info("【SpringBoot基础工程】启动成功!");
            log.info("【Swagger】{}", url);
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }


}

