package com.geekerstar.basic.module.metric;

import com.geekerstar.basic.module.metric.domain.entity.User;

/**
 * @author geekerstar
 * @date 2021/9/12 10:31
 * @description
 */
public class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}
