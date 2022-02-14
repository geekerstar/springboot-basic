package com.geekerstar.basic.module.neo4j.domain.entity;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Data
@Node
public class Neo4jPerson {
    @Id
    @GeneratedValue
    private Long id;
    @Property("cid")
    private int pid;
    private String name;
    private String character;
    private double money;
    private int age;
    private String description;
    @Relationship(type = "Friends", direction = Relationship.Direction.OUTGOING)
    private Set<Person> friendsPerson;


    public Set<Person> getFriendsPerson() {
        return friendsPerson;
    }

    public void setFriendsPerson(Set<Person> friendsPerson) {
        this.friendsPerson = friendsPerson;
    }

    public Neo4jPerson(Long id, int pid, String name, String character, double money, int age, String description) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.character = character;
        this.money = money;
        this.age = age;
        this.description = description;
    }

    public Neo4jPerson() {
    }

    public Long getId() {
        return id;
    }

}
