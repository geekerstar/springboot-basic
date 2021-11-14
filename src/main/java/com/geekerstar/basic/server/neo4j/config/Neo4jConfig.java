package com.geekerstar.basic.server.neo4j.config;

import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * @date 2021/10/31 22:49
 */
@Component
@EnableNeo4jRepositories(basePackages = "com.geekerstar.basic.server.neo4j.repository")
public class Neo4jConfig {
}
