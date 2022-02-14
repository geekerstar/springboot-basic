package com.geekerstar.basic.module.back.controller;

import com.geekerstar.basic.module.apm.threadlocal.UserContextHolder;
import com.geekerstar.basic.module.back.domain.common.Response;
import com.geekerstar.basic.module.back.domain.entity.User;
import com.geekerstar.basic.module.back.service.MetricService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2021/9/12 08:58
 * @description
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/metric")
@ApiSupport(order = 1)
public class MetricController {

    private final MetricService metricService;

    @GetMapping("/getThreadLocal")
    public Response<String> getThreadLocal() {
        User user = new User();
        user.setId(0L);
        user.setName("Geekerstar");
        UserContextHolder.holder.set(user);
        metricService.getThreadLocal();
        return Response.success();
    }
}
