package com.geekerstar.basic.server.neo4j.domain.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("SonNode")
@Data
@ToString
public class SonNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Relationship(type = "RelationEdge", direction = Relationship.Direction.INCOMING)
    private List<RelationNode> sets = new ArrayList<>();

    public SonNode(String name) {
        this.name = name;
    }

}
