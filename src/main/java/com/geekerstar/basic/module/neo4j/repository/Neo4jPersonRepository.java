//package com.geekerstar.basic.module.neo4j.repository;
//
//import com.geekerstar.basic.module.neo4j.domain.entity.Neo4jPerson;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.data.neo4j.repository.query.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface Neo4jPersonRepository extends Neo4jRepository<Neo4jPerson, Long> {
//    /**
//     * 查看money 大于指定值的Person 列表
//     */
//    //@Query("match(p:Person) where p.money>{0} return p")
//    @Query("match(p:Person) where p.money>{money} return p")
//    List<Neo4jPerson> personList(@Param("money") double money);
//
//    /**
//     * 指定开始的名字 和 结束的名字 查询最短路径  限定深度为4以层包含4
//     */
//    @Query("match p=shortestPath((person:Person{name:{startName}})-[*1..4]-(person2:Person {name:{endName}})) return p")
//    List<Neo4jPerson> shortestPath(@Param("startName") String startName, @Param("endName") String endName);
//
//    @Query("match p =(person:Person {name:{name}})-[*1..2]-(:Person) return p")
//    List<Neo4jPerson> personListDept(@Param("name") String name);
//}
