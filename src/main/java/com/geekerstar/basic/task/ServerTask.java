package com.geekerstar.basic.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.geekerstar.basic.module.back.domain.entity.Server;
import com.geekerstar.basic.module.back.domain.vo.ServerVO;
import com.geekerstar.basic.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author geekerstar
 * @date 2021/8/18 20:23
 * @description
 */
@Slf4j
@Component
public class ServerTask {
    @Autowired
    private SimpMessagingTemplate wsTemplate;

    private final String PUSH_SERVER = "/topic/server";

    /**
     * 按照标准时间来算，每隔 2s 执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void websocket() throws Exception {
        log.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        // 查询服务器状态
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        Dict dict = ServerUtil.wrapServerDict(serverVO);
        wsTemplate.convertAndSend(PUSH_SERVER, JSONUtil.toJsonStr(dict));
        log.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }
}

