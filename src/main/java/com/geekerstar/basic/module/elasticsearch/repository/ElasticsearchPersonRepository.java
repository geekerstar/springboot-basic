package com.geekerstar.basic.module.elasticsearch.repository;

import com.geekerstar.basic.module.back.domain.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author geekerstar
 * @date 2021/8/18 22:40
 * @description
 */
public interface ElasticsearchPersonRepository extends ElasticsearchRepository<Person, Long> {

    /**
     * 根据年龄区间查询
     *
     * @param min 最小值
     * @param max 最大值
     * @return 满足条件的用户列表
     */
    List<Person> findByAgeBetween(Integer min, Integer max);
}
