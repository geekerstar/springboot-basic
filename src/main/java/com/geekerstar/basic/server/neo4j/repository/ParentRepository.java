package com.geekerstar.basic.server.neo4j.repository;

import com.geekerstar.basic.server.neo4j.domain.entity.ParentNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends Neo4jRepository<ParentNode, Long> {
}
