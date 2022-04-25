package com.geekerstar.basic.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author geekerstar
 * @date 2022/3/8 17:42
 * https://mp.weixin.qq.com/s/DQOLGGhEA5Rfx2ML7U2vng
 * https://mp.weixin.qq.com/s/KFglHx5hDwM5arQS7Hj24g
 */
public class OptionalTest {

    public String getName(String name) {
        System.out.println(name);
        return name;
    }

    @Test
    public void test1(){
        String name1 = Optional.of("xxxx").orElse(getName("name1")); //output: method called
        String name2 = Optional.of("xxxx").orElseGet(() -> getName("name2")); //output:
        System.out.println(name1);
        System.out.println(name2);
    }

}
