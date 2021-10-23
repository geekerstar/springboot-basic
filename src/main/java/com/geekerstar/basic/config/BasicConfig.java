package com.geekerstar.basic.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author geekerstar
 * @date 2021/8/14 18:16
 * @description
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:basic.yml"}, factory = YamlConfig.class)
@ConfigurationProperties(prefix = "basic")
public class BasicConfig {
}
