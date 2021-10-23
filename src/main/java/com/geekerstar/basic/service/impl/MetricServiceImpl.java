package com.geekerstar.basic.service.impl;

import com.geekerstar.basic.common.UserContextHolder;
import com.geekerstar.basic.domain.entity.User;
import com.geekerstar.basic.service.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2021/9/12 08:59
 * @description
 */
@Slf4j
@Service
public class MetricServiceImpl implements MetricService {
    @Override
    public void getThreadLocal() {
        User user = UserContextHolder.holder.get();
        log.info("Remove前：{}", user);
        UserContextHolder.holder.remove();
        User user1 = UserContextHolder.holder.get();
        log.info("Remove后：{}", user1);
    }
}
