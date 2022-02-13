package com.geekerstar.basic.java8.stream;

import cn.hutool.json.JSONUtil;
import com.geekerstar.basic.java8.domain.Pool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2021/11/28 15:28
 * <p>
 * Java8使用Stream实现List中对象属性的合并（去重并求和）
 * https://www.cnblogs.com/louis-liu-oneself/p/14782152.html
 */
public class Fun1 {

    @Test
    public void test1() {
        List<Pool> list = new ArrayList<Pool>() {
            {
                add(new Pool("A", 1));
                add(new Pool("A", 2));
                add(new Pool("A", 3));
                add(new Pool("B", 4));
                add(new Pool("B", 5));
            }
        };

        List<Pool> result = merge(list);
        System.err.println(JSONUtil.toJsonStr(result));
    }

    /**
     * 使用Java8的流进行处理，将name相同的对象进行合并，将value属性求和
     */
    public static List<Pool> merge(List<Pool> list) {
        Map<String, Pool> map = new HashMap<String, Pool>();
        list.stream().forEach(pool -> {
            Pool last = map.get(pool.getName());
            if (null != last) {
                pool.setValue(pool.getValue() + last.getValue());
            }
            map.put(pool.getName(), pool);
        });
        return map.values().stream().collect(Collectors.toList());
    }

    /**
     * 使用Java8的流进行处理，将name相同的对象进行合并，将value属性求和
     */
    public static List<Pool> merge2(List<Pool> list) {
        List<Pool> result = list.stream()
                // 表示name为key，接着如果有重复的，那么从Pool对象o1与o2中筛选出一个，这里选择o1，
                // 并把name重复，需要将value与o1进行合并的o2, 赋值给o1，最后返回o1
                .collect(Collectors.toMap(Pool::getName, a -> a, (o1, o2) -> {
                    o1.setValue(o1.getValue() + o2.getValue());
                    return o1;
                })).values().stream().collect(Collectors.toList());
        return result;
    }
}
