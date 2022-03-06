package com.geekerstar.basic.java8.tree;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2022/3/5 11:38
 *
 * https://mp.weixin.qq.com/s?__biz=MzU1Nzg4NjgyMw==&mid=2247483974&idx=1&sn=d80567d84cb5186d8f5c349e83a38ef4&chksm=fc2fbe4ecb58375843fa0cc1841d4f3287908b93f2e757fb079215cae67e22af13565639aae0&mpshare=1&scene=1&srcid=&sharer_sharetime=1567399972668&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class TreeTest {

    @Test
    public void testTree() {
        //模拟从数据库查询出来
        List<Menu> menus = Arrays.asList(
                new Menu(1, "根节点", 0),
                new Menu(2, "子节点1", 1),
                new Menu(3, "子节点1.1", 2),
                new Menu(4, "子节点1.2", 2),
                new Menu(5, "根节点1.3", 2),
                new Menu(6, "根节点2", 1),
                new Menu(7, "根节点2.1", 6),
                new Menu(8, "根节点2.2", 6),
                new Menu(9, "根节点2.2.1", 7),
                new Menu(10, "根节点2.2.2", 7),
                new Menu(11, "根节点3", 1),
                new Menu(12, "根节点3.1", 11)
        );

        //获取父节点
        List<Menu> collect = menus.stream()
                .filter(m -> m.getParentId() == 0)
                .peek((m) -> m.setChildList(getChildrenList(m, menus)))
                .collect(Collectors.toList());
        System.out.println("-------转json输出结果-------");
        System.out.println(JSONUtil.toJsonStr(collect));
    }

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param all  所有节点
     * @return 根节点信息
     */
    private List<Menu> getChildrenList(Menu root, List<Menu> all) {
        return all.stream()
                .filter(m -> Objects.equals(m.getParentId(), root.getId()))
                .peek((m) -> m.setChildList(getChildrenList(m, all)))
                .collect(Collectors.toList());
    }
}
