package com.geekerstar.basic.elasticsearch;

import com.geekerstar.basic.BasicApplicationTests;
import com.geekerstar.basic.module.elasticsearch.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2021/8/18 22:41
 * @description
 */
@Slf4j
public class ElasticSearchTest extends BasicApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(Product.class);
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex() {
        boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("删除索引 = " + flg);
    }

    @Test
    public void insert() {
        Product product = new Product();
        product.setId(0L);
        product.setTitle("苹果电脑");
        product.setCategory("电脑");
        product.setPrice(0.0D);
        product.setImages("httpsdsdada");
        elasticsearchRestTemplate.save(product);
    }

    @Test
    public void update() {
        Document document = Document.create();
        document.put("title", "华为电脑");
        elasticsearchRestTemplate.update(UpdateQuery.builder("0").withDocument(document).build(), IndexCoordinates.of("product"));
    }

    @Test
    public void delete() {
        elasticsearchRestTemplate.delete("0", IndexCoordinates.of("product"));
    }

    @Test
    public void search() {
        Pageable pageable = PageRequest.of(0, 999);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        NativeSearchQuery query = builder.withQuery(QueryBuilders.multiMatchQuery("苹果", "title"))
                .withPageable(pageable)
                .build();
        SearchHits<Product> product = elasticsearchRestTemplate.search(query, Product.class, IndexCoordinates.of("product"));
        Stream<SearchHit<Product>> searchHitStream = product.get();
        List<SearchHit<Product>> collect = searchHitStream.collect(Collectors.toList());
        System.out.println(collect);
    }
}
