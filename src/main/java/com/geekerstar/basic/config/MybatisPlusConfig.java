package com.geekerstar.basic.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekerstar
 * @date 2021/8/14 18:12
 * @description
 */
@Configuration
@MapperScan("com.geekerstar.basic.mapper.*")
public class MybatisPlusConfig {
}
