package com.geekerstar.basic.domain.vo.server;

import com.geekerstar.basic.domain.entity.server.Sys;
import com.geekerstar.basic.domain.vo.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class SysVO {
    List<KV> data = Lists.newArrayList();

    public static SysVO create(Sys sys) {
        SysVO vo = new SysVO();
        vo.data.add(new KV("服务器名称", sys.getComputerName()));
        vo.data.add(new KV("服务器Ip", sys.getComputerIp()));
        vo.data.add(new KV("项目路径", sys.getUserDir()));
        vo.data.add(new KV("操作系统", sys.getOsName()));
        vo.data.add(new KV("系统架构", sys.getOsArch()));
        return vo;
    }
}
