package com.geekerstar.basic.module.apm;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.geekerstar.basic.constant.CommonConstant.TRACE_ID;

/**
 * @author geekerstar
 * @date 2021/10/23 09:50
 */
public class MetricInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestTraceId = request.getHeader("traceId");
        if (StringUtils.isNotBlank(requestTraceId)) {
            MDC.put(TRACE_ID, requestTraceId);
        } else {
            MDC.put(TRACE_ID, IdUtil.fastSimpleUUID());
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        MDC.clear();
    }
}
