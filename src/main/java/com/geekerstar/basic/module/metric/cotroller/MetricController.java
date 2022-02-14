package com.geekerstar.basic.module.metric.cotroller;

import com.geekerstar.basic.module.metric.UserContextHolder;
import com.geekerstar.basic.util.Response;
import com.geekerstar.basic.module.metric.domain.entity.User;
import com.geekerstar.basic.module.metric.service.MetricService;
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
