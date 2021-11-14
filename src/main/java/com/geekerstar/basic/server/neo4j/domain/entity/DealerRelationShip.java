package com.geekerstar.basic.server.neo4j.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.time.LocalDateTime;

@Data
@ToString
//@Relationship("manage")
@AllArgsConstructor
@NoArgsConstructor
public class DealerRelationShip {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 关系名
     */
    private String name;

    //    @StatNode
    private Dealer startDealer;

    //    @EndNode
    private Dealer endDealer;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public DealerRelationShip(Dealer startDealer, Dealer endDealer, String name, LocalDateTime createTime, LocalDateTime updateTime) {
        this.startDealer = startDealer;
        this.endDealer = endDealer;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
