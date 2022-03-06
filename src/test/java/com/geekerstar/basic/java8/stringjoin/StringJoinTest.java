package com.geekerstar.basic.java8.stringjoin;

import org.junit.Test;

import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * @author geekerstar
 * @date 2022/3/5 14:17
 */
public class StringJoinTest {

    @Test
    public void test1(){
        StringJoiner sj = new StringJoiner(",");
        IntStream.range(1,10).forEach(i->sj.add(i+""));
        System.out.println(sj);
    }
}
