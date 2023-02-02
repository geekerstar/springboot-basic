//package com.geekerstar.basic.module.neo4j.domain.entity;
//
//import lombok.Data;
//import lombok.ToString;
//import org.springframework.data.neo4j.core.schema.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@ToString
//@Node("ParentNode")
//public class ParentNode {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Property(name = "name")
//    private String name;
//
//    @Relationship(type = "RelationEdge")
//    private List<RelationNode> sonNodes = new ArrayList<>();
//
//
//    public ParentNode(String name) {
//        this.name = name;
//    }
//
//    public void addRelation(SonNode sonNode, String name) {
//        RelationNode relationNode = new RelationNode(this, name, sonNode);
//        sonNodes.add(relationNode);
//        sonNode.getSets().add(relationNode);
//    }
//
//}
