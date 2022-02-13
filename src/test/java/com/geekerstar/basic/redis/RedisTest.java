package com.geekerstar.basic.redis;

import com.geekerstar.basic.BasicApplicationTests;
import com.geekerstar.basic.module.back.domain.entity.User;
import com.geekerstar.basic.module.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author geekerstar
 * @date 2021/8/18 21:31
 * @description
 */
@Slf4j
public class RedisTest extends BasicApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 获取两次，查看日志验证缓存
     */
    @Test
    public void getTwice() {
        // 模拟查询id为1的用户
        User user1 = userService.get(1L);
        log.debug("【user1】= {}", user1);

        // 再次查询
        User user2 = userService.get(1L);
        log.debug("【user2】= {}", user2);
        // 查看日志，只打印一次日志，证明缓存生效
    }

    /**
     * 先存，再查询，查看日志验证缓存
     */
    @Test
    public void getAfterSave() {
        userService.saveOrUpdate(new User(4L, "测试中文"));

        User user = userService.get(4L);
        log.debug("【user】= {}", user);
        // 查看日志，只打印保存用户的日志，查询是未触发查询日志，因此缓存生效
    }

    /**
     * 测试删除，查看redis是否存在缓存数据
     */
    @Test
    public void deleteUser() {
        // 查询一次，使redis中存在缓存数据
        userService.get(1L);
        // 删除，查看redis是否存在缓存数据
        userService.delete(1L);
    }

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() {
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("【k1】= {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
//        String key = "geek:user:1";
//        redisCacheTemplate.opsForValue().set(key, new User(1L, "user1"));
//        // 对应 String（字符串）
//        User user = (User) redisCacheTemplate.opsForValue().get(key);
//        log.debug("【user】= {}", user);
    }
}
