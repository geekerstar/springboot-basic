package com.geekerstar.basic.module.neo4j.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node("dealer")
public class Dealer {

    /**
     * 测试必须要个@GeneratedValue作为id，否者无法插入
     */
    @Id
    @GeneratedValue
    private Long id;

    private Long dealerId;
    private String labelName;
    private String nikeName;
    private Double dealerPrice;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Relationship(type = "manage")
    List<DealerRelationShip> ships = new ArrayList<>();

    @Override
    public int hashCode() {
        int result = nikeName.hashCode();
        result = 17 * result + id.hashCode();
        result = 17 * result + id.hashCode();
        return result;
    }
}
