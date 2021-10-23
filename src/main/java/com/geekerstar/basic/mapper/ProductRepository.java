package com.geekerstar.basic.mapper;

import com.geekerstar.basic.domain.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2021/9/20 22:47
 * @description
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
}
