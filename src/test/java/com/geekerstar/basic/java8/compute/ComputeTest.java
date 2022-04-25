package com.geekerstar.basic.java8.compute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2022/3/8 19:56
 */
public class ComputeTest {

    private static void preJava8() {
        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for(String animal : animals){
            Integer count = map.get(animal);
            map.put(animal, count == null ? 1 : ++count);
        }
        System.out.println(map);
    }

    private static void inJava8() {
        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for(String animal : animals){
            map.compute(animal, (k, v) -> v == null ? 1 : ++v);
        }
        System.out.println(map);
    }


}
