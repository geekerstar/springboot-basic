package com.geekerstar.basic.redis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2021/8/20 19:22
 * @description Redis分布式锁
 * <p>
 * https://mp.weixin.qq.com/s?__biz=MzU5ODUwNzY1Nw==&mid=2247484164&idx=1&sn=210397905ef284c1d2756d1cdf73880f&chksm=fe426ae2c935e3f4cecd624f806c25177d5e383576a5f2f974761f62fd7d73a0d76810c2cdfb&mpshare=1&scene=1&srcid=#rd
 * <p>
 * https://mp.weixin.qq.com/s?__biz=MzU5ODUwNzY1Nw==&mid=2247484155&idx=1&sn=0c73f45f2f641ba0bf4399f57170ac9b&scene=21#wechat_redirect
 */
public class DistributeLock {

    @Test
    public void standalone() {
        // 构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.0.107:16379").setDatabase(1);
// 构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
// 设置锁定资源名称
        RLock disLock = redissonClient.getLock("DISLOCK");
        boolean isLock;
        try {
            //尝试获取分布式锁
            isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(15000);
            }
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            disLock.unlock();
        }
    }


    public void sentinel() {
        Config config = new Config();
        config.useSentinelServers().addSentinelAddress(
                        "redis://172.29.3.245:26378", "redis://172.29.3.245:26379", "redis://172.29.3.245:26380")
                .setMasterName("mymaster")
                .setPassword("a123456").setDatabase(0);
    }

    public void cluster() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                        "redis://172.29.3.245:6375", "redis://172.29.3.245:6376", "redis://172.29.3.245:6377",
                        "redis://172.29.3.245:6378", "redis://172.29.3.245:6379", "redis://172.29.3.245:6380")
                .setPassword("a123456").setScanInterval(5000);
    }

    public void redLock() {
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://172.29.1.180:5378")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient1 = Redisson.create(config1);

        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://172.29.1.180:5379")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient2 = Redisson.create(config2);

        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://172.29.1.180:5380")
                .setPassword("a123456").setDatabase(0);
        RedissonClient redissonClient3 = Redisson.create(config3);

        String resourceName = "REDLOCK";
        RLock lock1 = redissonClient1.getLock(resourceName);
        RLock lock2 = redissonClient2.getLock(resourceName);
        RLock lock3 = redissonClient3.getLock(resourceName);

        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        boolean isLock;
        try {
            isLock = redLock.tryLock(500, 30000, TimeUnit.MILLISECONDS);
            System.out.println("isLock = " + isLock);
            if (isLock) {
                //TODO if get lock success, do something;
                Thread.sleep(30000);
            }
        } catch (Exception e) {
        } finally {
            // 无论如何, 最后都要解锁
            System.out.println("");
            redLock.unlock();
        }
    }
}
