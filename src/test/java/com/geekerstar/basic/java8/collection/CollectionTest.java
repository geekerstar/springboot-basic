package com.geekerstar.basic.java8.collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2022/3/5 12:25
 */
public class CollectionTest {


    private static final List<Apple> appleList = Lists.newArrayList();

    static {
        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);
        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
    }

    // 分组
    @Test
    public void test1() {
        //List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));

        System.err.println("groupBy:" + groupBy);
    }

    /**
     * List转Map
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    @Test
    public void test2() {
        Map<Integer, Apple> appleMap = appleList.stream()
                .collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
    }

    /**
     * 求和
     */
    @Test
    public void test3() {
        //计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney);  //totalMoney:17.48
    }


}
