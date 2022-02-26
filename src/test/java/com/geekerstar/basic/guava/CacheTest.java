package com.geekerstar.basic.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author geekerstar
 * @date 2022/2/26 10:28
 *
 * https://mp.weixin.qq.com/s/n7B3eq90bV_rWeDoCWBMLg
 */
public class CacheTest {

    private String fetchValueFromServer(String key) {
        return key.toUpperCase();
    }

    @Test
    public void whenCacheMiss_thenFetchValueFromServer() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
                    // 当调用 LoadingCache 的 getUnchecked 或者 get方法时，Guava Cache 行为如下：
                    //- 缓存未命中时，同步调用 load 接口，加载进缓存，返回缓存值
                    //- 缓存命中，直接返回缓存值
                    //- 多线程缓存未命中时，A 线程 load 时，会阻塞 B 线程的请求，直到缓存加载完毕
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals("HELLO", cache.get("hello"));
        assertEquals(1, cache.size());
    }

    // 务必仅在预加载缓存这个场景使用 put，其他任何场景都应该使用 load 去触发加载缓存
    @Test
    public void whenPreloadCache_thenPut() {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        String key = "geek";
        cache.put(key,fetchValueFromServer(key));

        assertEquals(1, cache.size());
    }

    // 注意这是一个反面示例
    @Test
    public void wrong_usage_whenCacheMiss_thenPut() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return "";
                    }
                });

        String key = "geek";
        String cacheValue = cache.get(key);
        if ("".equals(cacheValue)) {
            cacheValue = fetchValueFromServer(key);
            cache.put(key, cacheValue);
        }
        cache.put(key, cacheValue);
        assertEquals(1, cache.size());
    }

    @Test
    public void whenReachMaxSize_thenEviction() throws ExecutionException {
        LoadingCache<String, String> cache =
                // 缓存固定数量的值
                CacheBuilder.newBuilder().maximumSize(3).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        cache.get("one");
        cache.get("two");
        cache.get("three");
        cache.get("four");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("one"));
        assertEquals("FOUR", cache.getIfPresent("four"));
    }

    // LRU 过期策略
    @Test
    public void whenReachMaxSize_thenEviction_LRU() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().maximumSize(3).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        cache.get("one");
        cache.get("two");
        cache.get("three");
        // access one
        cache.get("one");
        cache.get("four");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("two"));
        assertEquals("ONE", cache.getIfPresent("one"));
    }

    @Test
    public void whenEntryIdle_thenEviction()
            throws InterruptedException, ExecutionException {

        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        cache.get("geek");
        assertEquals(1, cache.size());

        cache.get("geek");
        Thread.sleep(2000);

        assertNull(cache.getIfPresent("geek"));
    }

    // 缓存失效
    @Test
    public void whenInvalidate_thenGetNull() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder()
                        .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) {
                                return fetchValueFromServer(key);
                            }
                        });

        String name = cache.get("geek");
        assertEquals("Geek", name);
        // 使用 void invalidate(Object key) 移除单个缓存，使用 void invalidateAll() 移除所有缓存。
        cache.invalidate("geek");
        assertNull(cache.getIfPresent("geek"));
    }

    @Test
    public void whenCacheRefresh_thenLoad()
            throws InterruptedException, ExecutionException {

        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws InterruptedException {
                        Thread.sleep(2000);
                        return key + ThreadLocalRandom.current().nextInt(100);
                    }
                });

        String oldValue = cache.get("geek");

        new Thread(() -> {
            // 手动刷新
            // refresh 方法将会触发 load 逻辑，尝试从数据源加载缓存。
            // 需要注意点的是，refresh 方法并不会阻塞 get 方法，所以在 refresh 期间，旧的缓存值依旧会被访问到，直到 load 完毕
            cache.refresh("geek");
        }).start();

        // make sure another refresh thread is scheduling
        Thread.sleep(500);

        String val1 = cache.get("geek");

        assertEquals(oldValue, val1);

        // make sure refresh cache
        Thread.sleep(2000);

        String val2 = cache.get("geek");
        assertNotEquals(oldValue, val2);

    }

    // 自动刷新
    @Test
    public void whenTTL_thenRefresh() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache =
                // refreshAfterWrite 同样不会阻塞 get 线程，依旧有访问旧值的可能性。
                CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return key + ThreadLocalRandom.current().nextInt(100);
                    }
                });

        String first = cache.get("kirito");
        Thread.sleep(1000);
        String second = cache.get("kirito");

        assertNotEquals(first, second);
    }

    // 缓存命中统计
    // Guava Cache 默认情况不会对命中情况进行统计，需要在构建 CacheBuilder 时显式配置 recordStats
    @Test
    public void whenRecordStats_thenPrint() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().maximumSize(100).recordStats().build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchValueFromServer(key);
                    }
                });

        cache.get("one");
        cache.get("two");
        cache.get("three");
        cache.get("four");

        cache.get("one");
        cache.get("four");

        CacheStats stats = cache.stats();
        System.out.println(stats);
    }

    // 缓存移除的通知机制
    // 我们希望对缓存失效进行一些监测，或者是针对失效的缓存做一些回调处理，就可以使用 RemovalNotification 机制
    @Test
    public void whenRemoval_thenNotify() throws ExecutionException {
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder().maximumSize(3)
                        // removalListener 可以给 LoadingCache 增加一个回调处理器，RemovalNotification 实例包含了缓存的键值对以及移除原因。
                        .removalListener(
                                cacheItem -> System.out.println(cacheItem + " is removed, cause by " + cacheItem.getCause()))
                        .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) {
                                return fetchValueFromServer(key);
                            }
                        });

        cache.get("one");
        cache.get("two");
        cache.get("three");
        cache.get("four");
    }
}
