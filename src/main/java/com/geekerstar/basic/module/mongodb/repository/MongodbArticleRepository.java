//package com.geekerstar.basic.module.mongodb.repository;
//
//import com.geekerstar.basic.module.mongodb.domain.entity.Article;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
///**
// * @author geekerstar
// * @date 2021/8/18 18:52
// * @description
// */
//public interface MongodbArticleRepository extends MongoRepository<Article, Long> {
//    /**
//     * 根据标题模糊查询
//     *
//     * @param title 标题
//     * @return 满足条件的文章列表
//     */
//    List<Article> findByTitleLike(String title);
//}
