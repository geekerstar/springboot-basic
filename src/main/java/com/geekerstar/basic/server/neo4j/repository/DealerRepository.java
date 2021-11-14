package com.geekerstar.basic.server.neo4j.repository;

import com.geekerstar.basic.server.neo4j.domain.entity.Dealer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface DealerRepository extends Neo4jRepository<Dealer, Long> {

    Dealer findByLabelName(String labelName);

    Dealer findByDealerId(Long dealerId);

    /**
     * 返回某个人下面的所有经销
     */
    @Query("match data=(na:dealer{dealerId:$dealerId})-[*1..1]->(nb:dealer) return data")
    List<Dealer> findShipByDealer(Long dealerId);
}
