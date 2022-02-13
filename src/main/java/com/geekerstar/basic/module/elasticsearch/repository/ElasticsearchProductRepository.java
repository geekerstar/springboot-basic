package com.geekerstar.basic.module.elasticsearch.repository;

import com.geekerstar.basic.module.back.domain.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2021/9/20 22:47
 * @description
 */
@Repository
public interface ElasticsearchProductRepository extends ElasticsearchRepository<Product, Long> {
}
