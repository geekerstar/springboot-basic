package com.geekerstar.basic.module.neo4j.domain.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@ToString
public class RelationNode {

    @Id
    @GeneratedValue
    private Long id;

    //    @StartNode
    private ParentNode parentNode;

    // 关系名
    private String name;

    //    @EndNode
    private SonNode sonNode;

    RelationNode(ParentNode parentNode, String name, SonNode sonNode) {
        this.parentNode = parentNode;
        this.name = name;
        this.sonNode = sonNode;
    }
}
