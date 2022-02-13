package com.geekerstar.basic.module.back.controller;

import cn.hutool.core.lang.Dict;
import com.geekerstar.basic.module.back.domain.entity.Server;
import com.geekerstar.basic.module.back.domain.vo.ServerVO;
import com.geekerstar.basic.util.ServerUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2021/8/18 20:28
 * @description
 */
@RestController
@RequestMapping("/server")
@ApiSupport(order = 5)
public class ServerController {

    @GetMapping
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }
}
