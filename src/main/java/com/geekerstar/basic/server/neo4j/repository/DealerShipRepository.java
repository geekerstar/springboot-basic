package com.geekerstar.basic.server.neo4j.repository;


import com.geekerstar.basic.server.neo4j.domain.entity.DealerRelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DealerShipRepository extends Neo4jRepository<DealerRelationShip, Long> {
}
