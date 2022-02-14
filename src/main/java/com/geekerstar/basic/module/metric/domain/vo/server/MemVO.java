package com.geekerstar.basic.module.metric.domain.vo.server;

import com.geekerstar.basic.module.metric.domain.server.Mem;
import com.geekerstar.basic.module.metric.domain.vo.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public static MemVO create(Mem mem) {
        MemVO vo = new MemVO();
        vo.data.add(new KV("内存总量", mem.getTotal() + "G"));
        vo.data.add(new KV("已用内存", mem.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", mem.getFree() + "G"));
        vo.data.add(new KV("使用率", mem.getUsage() + "%"));
        return vo;
    }
}
