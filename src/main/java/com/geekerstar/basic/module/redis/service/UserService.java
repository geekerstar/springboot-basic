package com.geekerstar.basic.module.redis.service;

import com.geekerstar.basic.module.metric.domain.entity.User;

/**
 * @author geekerstar
 * @date 2021/8/18 21:29
 * @description
 */
public interface UserService {
    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
