package com.geekerstar.basic.java8.collection;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author geekerstar
 * @date 2022/3/5 12:24
 */
@Data
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}
